package cart.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("사용자 엔티티 테스트")
public class UserEntityTest {

    @DisplayName("사용자 엔티티는 ID 를 통해서 동등을 비교한다.")
    @Test
    void userIsEquatable() {
        UserId userId = new UserId(1L);
        User hyeon9mak = new User("hyeon9mak@choi.go", "password");
        User choi = new User("choi@hyeon.gu", "password");

        UserEntity hyeon9makEntity = new UserEntity(userId, hyeon9mak);
        UserEntity choiEntity = new UserEntity(userId, choi);

        assertEquals(hyeon9makEntity, choiEntity);
    }
}
