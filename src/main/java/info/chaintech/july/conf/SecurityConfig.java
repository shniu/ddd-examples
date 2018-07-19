package info.chaintech.july.conf;

import info.chaintech.july.dao.UserRepository;
import info.chaintech.july.jwt.JwtAuthenticationEntryPoint;
import info.chaintech.july.jwt.JwtUserDetailsServiceImpl;
import info.chaintech.july.jwt.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author shniu
 * @date 2018-07-11 下午7:44
 * @email niushaohan@digcredit.com
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Bean
    public UserDetailsService jwtUserDetailsService(UserRepository userRepository) {
        return new JwtUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public JwtAuthenticationEntryPoint unauthorizedHandler() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                UserDetailsService jwtUserDetailsService) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()  // 关闭 csrf
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(
                        "/api/auth/**").permitAll()
                .anyRequest()
                .authenticated();

        JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(
                authenticationManagerBean(),
                jwtSecret,
                userDetailsService()
        );
        http.addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, "/api/auth/login")
                .and().ignoring()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/resources/**")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }
}
