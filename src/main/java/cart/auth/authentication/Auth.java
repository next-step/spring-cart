package cart.auth.authentication;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import org.springframework.util.StringUtils;

public class Auth {

    private static final int EMAIL_INDEX = 0;
    private static final int PASSWORD_INDEX = 1;
    private static final String DELIMITER = ":";

    private long id;
    private String email;
    private String password;

    public Auth(String email, String password) {
        validate(email, password);
        this.email = email;
        this.password = password;
    }

    public static Auth of(String decoded) {
        String[] decodedSplit = decoded.split(DELIMITER);
        return new Auth(decodedSplit[EMAIL_INDEX], decodedSplit[PASSWORD_INDEX]);
    }

    private void validate(String email, String password) {
        validateEmail(email);
        validatePassword(password);
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new ServiceException(ErrorType.AUTHENTICATION_FAILED);
        }
    }

    private void validatePassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new ServiceException(ErrorType.AUTHENTICATION_FAILED);
        }
    }

    public void updateId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
