package cart.config;

import cart.infra.AuthInfo;
import cart.infra.AuthorizationExtractor;
import cart.infra.BasicAuthorizationExtractor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class MyInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor<AuthInfo> auth = new BasicAuthorizationExtractor();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Optional<AuthInfo> optionalAuthInfo = auth.extract(request);
        return optionalAuthInfo.isPresent();
    }
}
