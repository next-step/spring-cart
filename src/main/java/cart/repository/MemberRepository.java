package cart.repository;

import cart.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Member> rowMapper = (resultSet, rowNum)
            -> new Member(resultSet.getLong("id"),
            resultSet.getString("email"),
            resultSet.getString("password"));


    public List<Member> findAll() {
        String sql = "SELECT ID, EMAIL, PASSWORD FROM MEMBER";
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Member find(String email, String password) {
        String sql = "SELECT ID, EMAIL, PASSWORD FROM MEMBER WHERE EMAIL = ? AND PASSWORD = ?";
        Optional<Member> member = Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, rowMapper, email, password));

        if (member.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        return member.get();
    }
}
