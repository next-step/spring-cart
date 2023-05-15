package cart.infrastructure.dao;

import cart.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@JdbcTest
class UsersDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private UsersDao usersDao;

    @BeforeEach
    void setUp() {
        usersDao = new UsersDao(jdbcTemplate, dataSource);
    }

    @Test
    void users_테이블에_유저_데이터를_삽입한다() {
        // given, when
        User insertedUser = insertUser("a@a.com", "passwordA");

        // then
        assertThat(insertedUser.getId()).isNotNull();
    }

    @Test
    void users_테이블에서_id로_유저_데이터를_찾는다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");

        // when
        User foundUser = assertDoesNotThrow(() -> usersDao.findById(insertedUser.getId()).get());

        // then
        assertThat(foundUser.getEmail()).isEqualTo(insertedUser.getEmail());
        assertThat(foundUser.getPassword()).isEqualTo(insertedUser.getPassword());
    }

    @Test
    void users_테이블에서_email로_유저_데이터를_찾는다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");

        // when
        User foundUser = assertDoesNotThrow(() -> usersDao.findByEmail(insertedUser.getEmail()).get());

        // then
        assertThat(foundUser.getEmail()).isEqualTo(insertedUser.getEmail());
        assertThat(foundUser.getPassword()).isEqualTo(insertedUser.getPassword());
    }

    @Test
    void users_테이블의_모든_유저_데이터를_찾는다() {
        // given
        List<User> insertedUsers = List.of(
                insertUser("a@a.com", "passwordA"),
                insertUser("b@b.com", "passwordB")
        );

        // when
        List<User> foundUsers = usersDao.findAll();

        // then
        assertThat(foundUsers).flatExtracting(User::getEmail).containsExactly("a@a.com", "b@b.com");
        assertThat(foundUsers).flatExtracting(User::getPassword).containsExactly("passwordA", "passwordB");
    }

    private User insertUser(String email, String password) {
        User givenUser = User.builder()
                .email(email)
                .password(password)
                .build();

        return usersDao.insert(givenUser);
    }
}
