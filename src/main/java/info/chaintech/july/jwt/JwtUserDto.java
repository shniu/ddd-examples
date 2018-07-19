package info.chaintech.july.jwt;

import info.chaintech.july.domain.Role;
import info.chaintech.july.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shniu
 * @date 2018-06-08 下午6:42
 * @email niushaohan@digcredit.com
 */

public class JwtUserDto extends User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapToGrantedAuthorities((List<Role>) super.getRoles());
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }

    @Override
    public String toString() {
        return "JwtUserDto(uid=" + getUid() +
                ", username=" + getUsername() +
                ", enabled=" + getEnabled() + ")";
    }
}
