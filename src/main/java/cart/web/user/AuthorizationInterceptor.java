package cart.web.user;

import cart.infrastructure.security.BasicAuthorizationParser;
import cart.service.user.UserService;
import cart.service.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);

        if (authorizationHeader == null) {
            throw new IllegalArgumentException("로그인이 필요합니다. 로그인 정보를 입력하세요.");
        }

        UserDto userDto = BasicAuthorizationParser.parse(authorizationHeader);
        request.setAttribute("current_user", userService.login(userDto.getEmail(), userDto.getPassword()));

        return true;
    }

}
