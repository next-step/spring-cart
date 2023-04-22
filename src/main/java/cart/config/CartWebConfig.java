package cart.config;

import cart.auth.MemberResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CartWebConfig implements WebMvcConfigurer {

    private final MemberResolver memberResolver;

    public CartWebConfig(MemberResolver memberResolver) {
        this.memberResolver = memberResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberResolver);
    }
}
