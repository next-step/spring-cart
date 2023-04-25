package cart.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("사용자 ID 테스트")
public class UserIdTest {

    @DisplayName("사용자 식별자는 동등성 비교가 가능하다.")
    @Test
    void userIdIsEquatable() {
        UserId userId = new UserId(1L);
        UserId userId2 = new UserId(1L);
        assertEquals(userId, userId2);
    }

    @DisplayName("사용자 식별자는 0 이상이어야 한다.")
    @Test
    void userIdShouldBePositive() {
        assertThrows(IllegalArgumentException.class, () -> new UserId(-1L));
    }
}
