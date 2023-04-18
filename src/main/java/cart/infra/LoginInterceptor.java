package cart.infra;

import cart.member.MemberService;
import cart.member.model.AuthInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private AuthorizationExtractor<AuthInfo> basicAuthorizationExtractor = new BasicAuthorizationExtractor();

    @Autowired
    MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);

        var member = memberService.login(authInfo);

        request.getSession().setAttribute("memberId", member.getId());

        return super.preHandle(request, response, handler);
    }

}
