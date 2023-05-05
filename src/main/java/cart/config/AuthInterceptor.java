package cart.config;

import cart.domain.AuthInfo;
import cart.service.AuthService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        AuthInfo authInfo = AuthExtractor.extractAuthInfo(authorization);
        if (authService.checkAuth(authInfo)) {
            return true;
        }
        throw new IllegalArgumentException("인증 실패. 다시 로그인 해주세요");
    }
}
