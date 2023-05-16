package cart.user.repository;

import cart.user.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public void insert(Member member) {
        String SQL = "INSERT INTO MEMBER(email, password) VALUES (?, ?)";
        jdbcTemplate.update(SQL, member.getEmail(), member.getPassword());
    }

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("created_at").toLocalDateTime());

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper);
    }

    public void update(Member member) {
        String SQL = "UPDATE MEMBER SET email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(SQL, member.getEmail(), member.getPassword(), member.getId());
    }

    public void deleteById(Long id) {
        String SQL = "DELETE FROM MEMBER WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public Optional<Member> findById(Long id) {
        String SQL = "SELECT * FROM MEMBER WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL , (rs, nowNum) -> new Member(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getTimestamp("created_at").toLocalDateTime()), id));
    }
}
