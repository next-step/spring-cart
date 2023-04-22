package cart.auth;

import cart.exception.AuthorizationException;
import cart.service.MemberService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;

public class ArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    public ArgumentResolver(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String token = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
            List<String> credentials = parseToken(token);
            Long id = memberService.checkAndGetId(credentials);
            return new AuthDto(id);
        } catch (NullPointerException e) {
            throw new AuthorizationException("사용자 정보가 없습니다.");
        }
    }

    private List<String> parseToken(String token) {
        String value = token.split(" ")[1];
        return Arrays.asList(new String(Base64.decodeBase64(value)).split(":"));
    }
}
