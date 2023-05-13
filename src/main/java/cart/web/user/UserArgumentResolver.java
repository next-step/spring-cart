package cart.web.user;

import cart.service.user.UserService;
import cart.web.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorizationHeader = webRequest.getHeader(AUTHORIZATION_HEADER_NAME);

        if (authorizationHeader == null) {
            throw new IllegalArgumentException("로그인이 필요합니다. 로그인 정보를 입력하세요.");
        }

        UserDto userDto = BasicAuthorizationParser.parse(authorizationHeader);
        return userService.login(userDto.getEmail(), userDto.getPassword());
    }

}
