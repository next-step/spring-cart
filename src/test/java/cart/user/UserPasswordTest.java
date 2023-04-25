package cart.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 비밀번호 테스트")
class UserPasswordTest {

    @DisplayName("사용자 비밀번호는 공백으로 이루어질 수 없다.")
    @Test
    void userPasswordCannotBeBlank() {
        assertThrows(IllegalArgumentException.class, () -> new UserPassword(" "));
    }

    @DisplayName("사용자 비밀번호는 null이 될 수 없다.")
    @Test
    void userPasswordCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new UserPassword(null));
    }

    @DisplayName("사용자 비밀번호는 동등성 비교가 가능하다.")
    @Test
    void userPasswordIsEquatable() {
        var userPassword = new UserPassword("비밀번호");
        assertEquals(userPassword, new UserPassword("비밀번호"));
    }
}