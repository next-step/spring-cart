package cart.authenticate;

import cart.user.application.usecase.UserLoginUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserLoginUseCase userLoginUseCase;

    public WebMvcConfig(UserLoginUseCase userLoginUseCase) {
        this.userLoginUseCase = userLoginUseCase;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthArgumentResolver(userLoginUseCase));
    }
}
