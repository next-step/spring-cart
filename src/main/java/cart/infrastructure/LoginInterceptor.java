package cart.infrastructure;

import cart.service.CartService;
import cart.domain.Member;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private CartService cartService;

    public LoginInterceptor(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthorizationExtractor<Member> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
        Member member=  basicAuthorizationExtractor.extract(request);
        
        request.getSession().setAttribute("email", member.getEmail());

        return super.preHandle(request, response, handler);
    }
}
