package cart.authenticate;

import cart.user.application.usecase.UserLoginUseCase;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.security.sasl.AuthenticationException;
import java.util.Arrays;
import java.util.List;

public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private static final int EMAIL_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;
    private static final String BEARER_TOKEN_REGEX = " ";
    private static final int TOKEN_INDEX = 1;
    private static final String EMAIL_PASSWORD_REGEX = ":";

    private final UserLoginUseCase userLoginUseCase;

    public AuthArgumentResolver(UserLoginUseCase userLoginUseCase) {
        this.userLoginUseCase = userLoginUseCase;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String authorizationValue = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
            List<String> credentials = parseToken(authorizationValue);
            String email = credentials.get(EMAIL_INDEX);
            String password = credentials.get(PASSWORD_INDEX);
            return userLoginUseCase.login(email, password);
        } catch (Exception e) {
            throw new AuthenticationException("인증 관련 오류가 발생했습니다.");
        }
    }

    private List<String> parseToken(String authorizationValue) {
        String token = authorizationValue.split(BEARER_TOKEN_REGEX)[TOKEN_INDEX];
        String decodedValue = new String(Base64.decodeBase64(token));
        return Arrays.asList(decodedValue.split(EMAIL_PASSWORD_REGEX));
    }
}
