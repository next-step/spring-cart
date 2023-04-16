package cart.auth;

import cart.controller.response.MemberResponse;
import cart.service.MemberService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.InvalidParameterException;

public class BasicAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String BASIC_TYPE = "Basic";
    private static final String SEPARATOR = ":";
    private static final int AUTH_INFO_DATA_SIZE = 2;
    private final MemberService memberService;

    public BasicAuthArgumentResolver(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthInfo.class) && parameter.getParameterType().equals(MemberResponse.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.toLowerCase().startsWith(BASIC_TYPE.toLowerCase())) {
            return null;
        }
        return parseAuthData(authorization);
    }

    private MemberResponse parseAuthData(String authorization) {
        String authHeaderValue = authorization.substring(BASIC_TYPE.length()).trim();
        byte[] decodedBytes = Base64.decodeBase64(authHeaderValue);
        String decodedString = new String(decodedBytes);
        String[] authInfoArray = decodedString.split(SEPARATOR);

        if (authInfoArray.length != AUTH_INFO_DATA_SIZE) {
            throw new InvalidParameterException();
        }

        return memberService.find(authInfoArray[0], authInfoArray[1]);
    }

}
