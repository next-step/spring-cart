package cart.auth.dao;

import cart.auth.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        String sql = "select email, password from users";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    User prodouct = new User(
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return prodouct;
                });
    }

}
