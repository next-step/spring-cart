package cart.global.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authString = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (!StringUtils.hasText(authString)) {
            throw new AuthenticationException("인증 정보가 없습니다");
        }
        return super.preHandle(request, response, handler);
    }
}
