package transmatter.platform.administration.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import transmatter.platform.administration.security.entity.Admin;
import transmatter.platform.administration.security.entity.Authority;
import transmatter.platform.administration.security.entity.JwtUser;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Admin admin) {
        return new JwtUser(
                admin.getId(),
                admin.getUsername(),
                admin.getFirstname(),
                admin.getLastname(),
                admin.getEmail(),
                admin.getPassword(),
                mapToGrantedAuthorities(admin.getAuthorities()),
                admin.getEnabled(),
                admin.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
