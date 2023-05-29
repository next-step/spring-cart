package cart.config;

import cart.exception.NotFoundEntityException;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.MemberService;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

  private final MemberService memberService;
  private final BasicAuthorizationExtractor basicAuthorizationExtractor;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws AuthenticationException {
    String header = request.getHeader("Authorization");
    if (header == null) {
      throw new AuthenticationException("로그인이 필요합니다. 로그인 정보를 입력하세요.");
    }

    String email = basicAuthorizationExtractor.extract(request).getEmail();
    String password = basicAuthorizationExtractor.extract(request).getPassword();
    Object user = memberService.login(email, password);

    if (user == null) {
      throw new NotFoundEntityException("사용자");
    }

    request.setAttribute("current_user", user);

    return true;
  }
}
