package cart.service;

import cart.Exception.AuthorizationException;
import cart.dao.AuthDAO;
import cart.dto.AuthInfo;
import cart.infrastructure.AuthorizationExtractor;
import cart.infrastructure.BasicAuthorizationExtractor;
import java.net.http.HttpRequest;
import java.nio.file.AccessDeniedException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class AuthService {

    private static final Logger logger = LogManager.getLogger(AuthService.class);
    private AuthDAO authDAO;

    private AuthorizationExtractor<AuthInfo> basicAuthorizationExtractor = new BasicAuthorizationExtractor();

    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public AuthInfo checkBasicAuth(String Authorization) throws AccessDeniedException {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(Authorization);
        if (checkInvalidLogin(authInfo.getEmail(), authInfo.getPassword())) {
            throw new AuthorizationException();
        }
        return authInfo;
    }

    private boolean checkInvalidLogin(String email, String password) {
        return authDAO.checkMember(email, password) < 1;
    }

}
