package cart.config;

import cart.Service.MemberService;
import cart.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private MemberService memberService;

    public AuthInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthorizationExtractor<Member> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
        Member member=  basicAuthorizationExtractor.extract(request);

        if(!memberService.certification(member)){
            throw  new Exception("인증에 실패하였습니다.");
        }
        request.setAttribute("email", member.getEmail());

        return super.preHandle(request, response, handler);
    }
}
