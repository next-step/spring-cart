package cart.config;

import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.MemberService;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  private final BasicAuthorizationExtractor basicAuthorizationExtractor = new BasicAuthorizationExtractor();
  private final MemberService memberService;

  public UserArgumentResolver(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    log.info("supportsParameter");
    return parameter.hasParameterAnnotation(Login.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
    String email = basicAuthorizationExtractor.extract(httpServletRequest).getEmail();
    String password = basicAuthorizationExtractor.extract(httpServletRequest).getPassword();

    return memberService.login(email, password);
  }
}
