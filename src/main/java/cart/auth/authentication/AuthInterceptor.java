package cart.auth.authentication;

import cart.auth.application.AuthService;
import cart.exception.ErrorType;
import cart.exception.ServiceException;
import cart.member.domain.Member;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final int TYPE_INDEX = 0;
    private static final int CREDENTIAL_INDEX = 1;
    private static final String DELIMITER = " ";
    private static final String BASIC_AUTH = "Basic";
    private static final String AUTHORIZATION = "Authorization";

    private final AuthService authService;

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(AUTHORIZATION);
        Auth auth = getAuth(authorization);

        Member member = findMember(auth);
        auth.updateId(member.getId());

        SecurityContextHolder.setContext(auth);

        return true;
    }

    private Auth getAuth(String authorization) {
        String[] split = authorization.split(DELIMITER);
        String type = split[TYPE_INDEX];
        String credential = split[CREDENTIAL_INDEX];

        if (StringUtils.hasText(type) && !type.equals(BASIC_AUTH)) {
            throw new ServiceException(ErrorType.AUTHENTICATION_FAILED);
        }

        String decoded = new String(Base64.decodeBase64(credential));
        return Auth.of(decoded);
    }

    private Member findMember(Auth auth) {
        return authService.findByEmail(auth.getEmail());
    }

}
