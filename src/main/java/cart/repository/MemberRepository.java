package cart.repository;

import cart.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Member> findAll() {
    String sql = "SELECT * FROM member";
    return jdbcTemplate.query(sql, ((rs, rowNum) ->
        Member.builder()
            .id(rs.getLong("id"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .build()));
  }
}
