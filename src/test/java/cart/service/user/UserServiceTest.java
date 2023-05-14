package cart.service.user;

import cart.domain.user.User;
import cart.infrastructure.dao.UsersDao;
import cart.infrastructure.security.AuthenticationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest
class UserServiceTest {

    private static final User USER = User.builder()
            .email("a@a.com")
            .password("passwordA")
            .build();

    @Autowired
    private UserService userService;
    @Autowired
    private UsersDao usersDao;

    @Test
    void login_valid() {
        // given
        User insertedUser = usersDao.insert(USER);

        // when
        User loginedUser = userService.login("a@a.com", "passwordA");

        // then
        assertThat(loginedUser).isEqualTo(insertedUser);
        assertThat(loginedUser.getEmail()).isEqualTo(insertedUser.getEmail());
        assertThat(loginedUser.getPassword()).isEqualTo(insertedUser.getPassword());
    }

    @Test
    void login_invalid_nonexistent_email() {
        // given
        User insertedUser = usersDao.insert(USER);

        // when, then
        assertThatThrownBy(() -> userService.login("b@b.com", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void login_invalid_incorrect_password() {
        // given
        User insertedUser = usersDao.insert(USER);

        // when, then
        assertThatThrownBy(() -> userService.login("a@a.com", "passwordB"))
                .isInstanceOf(AuthenticationException.class);
    }
}
