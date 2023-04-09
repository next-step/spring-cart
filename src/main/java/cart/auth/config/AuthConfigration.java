package cart.auth.config;

import cart.auth.infrastructure.BasicAuthorizationExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfigration {

    @Bean
    public BasicAuthorizationExtractor basicAuthorizationExtractor(){
        return new BasicAuthorizationExtractor();
    }

}
