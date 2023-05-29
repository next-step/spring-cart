package cart.repository;

import cart.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert jdbcInsert;

  public MemberRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("member")
        .usingGeneratedKeyColumns("id");
  }

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

  public Member insert(Member member) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
    Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

    return Member.builder()
        .id(id)
        .email(member.getEmail())
        .password(member.getPassword())
        .build();
  }
}
