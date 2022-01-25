package io.facuf.userservice.security;


public class Constants {

    public static class JwtConfig {

        public static final String secretKey = "facuSecretJsonSignature";
        public static final Long timeInMillisToExpire = 1500000L;
    }
}
