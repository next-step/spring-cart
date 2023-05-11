package cart.auth.authentication;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthTest {

    @DisplayName("email이 없으면 예외가 발생한다.")
    @Test
    void validateEmail() {
        // then
        assertThatThrownBy(() -> new Auth("", "password"))
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.AUTHENTICATION_FAILED.getMessage());
    }

    @DisplayName("password가 없으면 예외가 발생한다.")
    @Test
    void validatePassword() {
        // then
        assertThatThrownBy(() -> new Auth("email", ""))
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.AUTHENTICATION_FAILED.getMessage());
    }

}
