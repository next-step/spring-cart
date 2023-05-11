package cart.member.infrastrucure;

import cart.member.domain.Member;
import cart.member.domain.MemberRepository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Member> findAll() {
        String sql = "SELECT id, email, password FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) ->
        new Member(rs.getLong("id"),
            rs.getString("email"),
            rs.getString("password"));
}
