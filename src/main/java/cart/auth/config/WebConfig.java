package cart.auth.config;

import cart.auth.application.AuthService;
import cart.auth.authentication.AuthArgumentResolver;
import cart.auth.authentication.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthService authService;

    public WebConfig(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(authService)).addPathPatterns("/carts/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthArgumentResolver());
    }
}
