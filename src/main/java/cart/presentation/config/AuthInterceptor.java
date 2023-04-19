package cart.presentation.config;

import cart.domain.member.MemberService;
import cart.domain.member.query.CheckMemberQuery;
import cart.presentation.util.AuthHeaderParser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    public AuthInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        AuthInfo authInfo = AuthHeaderParser.parse(authHeader);
        if (memberService.checkMember(new CheckMemberQuery(authInfo.getEmail(), authInfo.getPassword()))) {
            return true;
        }
        throw new IllegalArgumentException("회원 정보가 올바르지 않습니다.");
    }
}
