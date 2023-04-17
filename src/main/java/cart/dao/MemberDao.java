package cart.dao;

import cart.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Member> selectMember() {
        String sql = "SELECT email , password   FROM MEMBER";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Member member = new Member(
                            rs.getString("email"),
                            rs.getString("password")

                    );
                    return member;
                });
    }

    public boolean isValidMember(String email, String password) {
        String sql = "SELECT count(1) cnt FROM MEMBER where member_email = ? and member_password = ?";

        int cnt = jdbcTemplate.queryForObject(sql, Integer.class, email, password);

        if (cnt > 0) {
            return true;
        } else {
            return false;
        }
    }
}
