package cart.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 이메일 테스트")
class UserEmailTest {

    @DisplayName("이메일 규칙에 올바르지 않으면 생성할 수 없다.")
    @Test
    void create() {
        assertThrows(IllegalArgumentException.class, () -> new UserEmail("test"));
    }

    @DisplayName("사용자 이메일은 동등성 비교가 가능하다.")
    @Test
    void userEmailIsEquatable() {
        UserEmail userEmail = new UserEmail("hyeon9mak@choi.go");
        UserEmail hyeon9mak = new UserEmail("hyeon9mak@choi.go");
        assertEquals(userEmail, hyeon9mak);
    }
}