package cart.config;

import cart.config.data.AuthInfo;
import cart.controller.dto.response.UserResponse;
import cart.domain.User;
import cart.exception.JwpCartApplicationException;
import cart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import static cart.exception.ErrorCode.INVALID_AUTHORIZATION;


@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            throw new JwpCartApplicationException(INVALID_AUTHORIZATION);
        }
        AuthInfo authInfo = convert(authorization);
        User user = userService.login(authInfo);
        return UserResponse.of(user);
    }

    private AuthInfo convert(String authorization) {
        String literal = authorization.replace("Basic ", "");
        String decoded = new String(Base64.getDecoder().decode(literal.getBytes()));
        String[] userInfo = decoded.split(":");
        return new AuthInfo(userInfo[0], userInfo[1]);
    }
}
