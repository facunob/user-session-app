package io.facuf.userservice.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    ROLE_USER {
        @Override
        public Set<String> permissions() {
            return new HashSet<>(
                    Arrays.asList(
                            Permission.User.READ
                    )
            );
        }
    },
    ROLE_ADMIN {
        @Override
        public Set<String> permissions() {
            return new HashSet<>(
                    Arrays.asList(
                            Permission.User.DELETE,
                            Permission.User.WRITE,
                            Permission.User.READ
                    )
            );
        }
    };

    public abstract Set<String> permissions();
}
