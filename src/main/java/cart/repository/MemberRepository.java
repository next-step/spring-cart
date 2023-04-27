package cart.repository;

import cart.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> findAll() {
        String sql = "SELECT id, email, password FROM MEMBER";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Member member = new Member(
                            resultSet.getLong("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return member;
                }
        );
    }

    public Member findMember(String email) {
        String sql = "SELECT id, email, password FROM MEMBER" +
                " WHERE email = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> {
                    Member member = new Member(
                            resultSet.getLong("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                    return member;
                },
                email
        );
    }
}
