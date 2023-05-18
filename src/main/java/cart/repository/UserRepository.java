package cart.repository;

import cart.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<User> getAll(){
    String sql = "SELECT * FROM member";
    return jdbcTemplate.query(sql, (rs, rowNum) ->
        User.builder()
            .id(rs.getLong("id"))
            .email(rs.getString("email"))
            .password(rs.getString(("password")))
            .build());
  }
}
