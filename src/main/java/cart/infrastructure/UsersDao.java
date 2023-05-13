package cart.infrastructure;

import cart.domain.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<User> rowMapper;

    public UsersDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper();
    }

    private RowMapper<User> rowMapper() {
        return (resultSet, rowNum) -> User.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }

    public User insert(User user) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return User.builder()
                .id(id)
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, email));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, rowMapper);
    }
}
