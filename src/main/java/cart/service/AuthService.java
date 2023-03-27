package cart.service;

import cart.dao.AuthDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LogManager.getLogger(AuthService.class);
    private AuthDAO authDAO;

    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public boolean checkInvalidLogin(String email, String password) {
        return authDAO.checkMember(email, password) < 1;
    }

}
