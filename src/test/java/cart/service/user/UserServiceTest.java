package cart.service.user;

import cart.domain.user.User;
import cart.infrastructure.dao.UsersDao;
import cart.infrastructure.security.AuthenticationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest
public class UserServiceTest {

    public static final User USER_1 = User.builder()
            .id(1L)
            .email("a@a.com")
            .password("passwordA")
            .build();

    public static final User USER_2 = User.builder()
            .id(2L)
            .email("b@b.com")
            .password("passwordB")
            .build();

    @Autowired
    private UserService userService;
    @Autowired
    private UsersDao usersDao;

    @Test
    void 모든_유저를_조회한다() {
        // given
        usersDao.insert(USER_1);
        usersDao.insert(USER_2);

        // when
        List<User> users = userService.findAll();

        // then
        assertThat(users).flatExtracting(User::getEmail)
                .containsExactly("a@a.com", "b@b.com");
        assertThat(users).flatExtracting(User::getPassword)
                .containsExactly("passwordA", "passwordB");
    }

    @Test
    void 정상_유저가_로그인_한다() {
        // given
        User insertedUser = usersDao.insert(USER_1);

        // when
        User loginedUser = userService.login("a@a.com", "passwordA");

        // then
        assertThat(loginedUser).isEqualTo(insertedUser);
        assertThat(loginedUser.getEmail()).isEqualTo(insertedUser.getEmail());
        assertThat(loginedUser.getPassword()).isEqualTo(insertedUser.getPassword());
    }

    @Test
    void 비정상_유저가_로그인_한다_존재하지_않는_이메일인_경우() {
        // given
        User insertedUser = usersDao.insert(USER_1);

        // when, then
        assertThatThrownBy(() -> userService.login("b@b.com", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상_유저가_로그인_한다_비밀번호가_틀린_경우() {
        // given
        User insertedUser = usersDao.insert(USER_1);

        // when, then
        assertThatThrownBy(() -> userService.login("a@a.com", "passwordB"))
                .isInstanceOf(AuthenticationException.class);
    }
}
