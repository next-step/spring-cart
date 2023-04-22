package cart.auth;

import cart.domain.Member;
import cart.domain.Members;
import cart.dto.MemberToken;
import cart.exception.MemberException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
public class MemberResolver implements HandlerMethodArgumentResolver {

    private final Members members;

    public MemberResolver(Members members) {
        this.members = members;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MemberAuth.class);
    }

    @Override
    public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final MemberToken memberToken = getMemberToken(webRequest);
        final Member member = members.findByEmail(memberToken.getEmail()).orElseThrow(MemberException::new);
        member.authorize(memberToken.getEmail(), member.getPassword());
        return member;
    }

    private MemberToken getMemberToken(NativeWebRequest webRequest) {
        try {
            final String authorization = ((HttpServletRequest) webRequest.getNativeRequest()).getHeader("Authorization");
            if (authorization == null) {
                throw new MemberException("토큰 정보가 없습니다.");
            }

            final String authMethod = authorization.split(" ")[0];
            final String token = authorization.split(" ")[1];
            if (!authMethod.equals("Basic")) {
                throw new MemberException("지원하지 않는 토큰 인증 방식입니다.");
            }

            final String decodedToken = new String(Base64.getUrlDecoder().decode(token));
            final int lastIndex = decodedToken.lastIndexOf(":");
            final String email = decodedToken.substring(0, lastIndex);
            final String password = decodedToken.substring(lastIndex + 1);
            return new MemberToken(email, password);
        } catch (Exception e) {
            throw new MemberException("올바르지 않은 사용자 정보입니다.");
        }
    }
}
