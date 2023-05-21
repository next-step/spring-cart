package cart.repository;

import cart.domain.Member;
import java.util.List;
import java.util.Optional;
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

  public Optional<Member> findByEmail(String email) {
    String sql = "SELECT * FROM member WHERE email = ?";
    List<Member> members = jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) ->
        Member.builder()
            .id(rs.getLong("id"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .build());

    return members.stream().findFirst();
  }

}
