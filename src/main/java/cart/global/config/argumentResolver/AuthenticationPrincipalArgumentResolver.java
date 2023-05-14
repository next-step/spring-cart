package cart.global.config.argumentResolver;

import cart.auth.AuthInfo;
import cart.auth.AuthPrincipal;
import cart.member.domain.dto.MemberDto;
import cart.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws AuthenticationException {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        MemberDto member = getMemberFromAuth(request);
        return AuthPrincipal.from(member);
    }

    private MemberDto getMemberFromAuth(HttpServletRequest request) throws AuthenticationException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            throw new AuthenticationException("인증 정보가 없습니다");
        }
        AuthInfo authInfo = convert(authorization);
        return memberService.getMember(authInfo.getUsername(), authInfo.getPassword());
    }

    private AuthInfo convert(String basicAuthLiteral) {
        String authLiteral = basicAuthLiteral.replace("Basic ", "");
        String decodedString
                = new String(Base64.getDecoder().decode(authLiteral.getBytes()));

        String[] authArray = decodedString.split(":");
        return new AuthInfo(authArray[0], authArray[1]);
    }
}
