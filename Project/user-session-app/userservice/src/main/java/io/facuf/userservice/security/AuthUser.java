package io.facuf.userservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUser extends User {

    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public AuthUser(String username, String password, Long userId, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
        this.userId = userId;
    }

}
