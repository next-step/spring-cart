package cart.infra.jdbc;

import cart.domain.entity.Member;
import cart.domain.repository.MemberRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;

@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public Member findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM members WHERE email = ? and password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, getRowMapper(), email, password);
        } catch (Exception e) {
            throw new NoSuchElementException("해당 회원을 찾을 수 없습니다.");
        }
    }

    @Override
    public void insert(Member member) {
        String sql = "INSERT INTO members (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }

    private RowMapper<Member> getRowMapper() {
        return (rs, rowNum) -> {
            return new Member(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        };
    }
}
