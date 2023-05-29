package cart.config;

import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.MemberService;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final MemberService memberService;
  private final BasicAuthorizationExtractor basicAuthorizationExtractor;
  public WebConfig(MemberService memberService,
      BasicAuthorizationExtractor basicAuthorizationExtractor) {
    this.memberService = memberService;
    this.basicAuthorizationExtractor = basicAuthorizationExtractor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthorizationInterceptor(memberService,basicAuthorizationExtractor))
        .addPathPatterns("/api/v1/carts/**");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new UserArgumentResolver(memberService));
  }
}
