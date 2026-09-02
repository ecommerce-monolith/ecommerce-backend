package com.huyhn.ecommerce_backend.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppPropertiesConfig {

    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {

        private final Authentication authentication = new Authentication();

        @Getter
        @Setter
        public static class Authentication {

            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            public static class Jwt {
                private long tokenValidityInSeconds;
                private long tokenValidityInSecondsForRememberMe;
                private String base64Secret;
            }
        }
    }
}
