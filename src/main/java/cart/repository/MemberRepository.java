package cart.repository;

import cart.domain.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Member> rowMapper = (resultSet, rowNum)
            -> new Member(resultSet.getLong("id"),
            resultSet.getString("email"),
            resultSet.getString("password"));


    public List<Member> findAll() {
        final String sql = "SELECT ID, EMAIL, PASSWORD FROM MEMBER";
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Member find(String email, String password) {
        final String sql = "SELECT ID, EMAIL, PASSWORD FROM MEMBER WHERE EMAIL = ? AND PASSWORD = ?";

        final Member member;
        try {
            member = this.jdbcTemplate.queryForObject(sql, rowMapper, email, password);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        return member;
    }
}
