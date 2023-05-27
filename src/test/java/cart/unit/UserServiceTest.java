package cart.unit;

import cart.config.data.AuthInfo;
import cart.controller.dto.response.UsersResponse;
import cart.domain.User;
import cart.exception.JwpCartApplicationException;
import cart.repository.UserRepository;
import cart.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    public static final User SUCCESS_USER = new User("admin@gmail.com", "aaaa12345");
    public static final User FAILED_USER = new User("jisoo@gmail.com", "123ffff");


    @DisplayName("유저 정보 목록 조회 테스트")
    @Test
    void findAllTest() {
        // when
        UsersResponse usersResponse = userService.findAll();

        // then
        assertAll(
                () -> assertThat(usersResponse.getUserResponses().get(0).getEmail()).isEqualTo(SUCCESS_USER.getEmail()),
                () -> assertThat(usersResponse.getUserResponses().get(0).getPassword()).isEqualTo(SUCCESS_USER.getPassword())
        );
    }

    @DisplayName("유저 login 테스트")
    @Test
    void loginTest() {

        // when & then
        assertDoesNotThrow(() -> userService.login(new AuthInfo(SUCCESS_USER.getEmail(), SUCCESS_USER.getPassword())));
    }


    @DisplayName("유저 login 실패 테스트")
    @Test
    void loginExceptionTest() {

        // when & then
        assertThatThrownBy(() -> userService.login(new AuthInfo(FAILED_USER.getEmail(), FAILED_USER.getPassword())))
                .isInstanceOf(JwpCartApplicationException.class);
    }
}
