package cart.infra.jdbc;

import cart.domain.entity.Member;
import cart.domain.repository.MemberRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Member(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        });
    }
}
