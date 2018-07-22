package info.chaintech.july.jwt;

import info.chaintech.july.dao.UserRepository;
import info.chaintech.july.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * @author shniu
 * @date 2018-06-08 下午6:40
 * @email niushaohan@digcredit.com
 */

@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            String err = String.format("使用用户名 %s 未查询到用户", username);
            log.warn(err);
            throw new UsernameNotFoundException(err);
        }

        JwtUserDto jwtUserDto = new JwtUserDto();
        try {
            BeanUtils.copyProperties(jwtUserDto, optionalUser.get());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("User convert to JwtUserDto occur error, please check it, error: {}", e);
        }
        return jwtUserDto;
    }
}
