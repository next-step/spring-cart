package cart.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDAO {

    private final RowMapper<Integer> actorRowMapper = (resultSet, rowNum) -> {
        return resultSet.getInt("member_count");
    };
    private JdbcTemplate jdbcTemplate;

    public AuthDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int checkMember(String email, String password) {
        String sql = "select count(1) as member_count from member where email = ? and password = ?";
        return this.jdbcTemplate.query(sql, actorRowMapper, email, password).size();
    }
}
