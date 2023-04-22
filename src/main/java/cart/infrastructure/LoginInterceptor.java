package cart.infrastructure;

import cart.service.CartService;
import cart.domain.Member;
import cart.service.MemberService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private MemberService memberService;

    public LoginInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthorizationExtractor<Member> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
        Member member=  basicAuthorizationExtractor.extract(request);

        if(!memberService.validMember(member)){
            throw  new AuthorizationException("인증에 실패하였습니다.");
        }

        request.getSession().setAttribute("email", member.getEmail());

        return super.preHandle(request, response, handler);
    }
}
