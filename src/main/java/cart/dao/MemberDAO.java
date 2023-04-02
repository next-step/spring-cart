package cart.dao;

import cart.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAO {
    private JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> selectMembers() {
        String sql = "SELECT member_email ,member_password FROM MEMBER";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    return new Member(
                            rs.getString("member_email")
                            , rs.getString("member_password")
                    );
                });
    }

    public boolean countMember(Member member) {
        String sql = "SELECT count(1) cnt FROM MEMBER where member_email = ? and member_password = ?";

        int cnt = jdbcTemplate.queryForObject(sql, Integer.class, member.getEmail(), member.getPassword());

        if (cnt > 0) {
            return true;
        } else {
            return false;
        }
    }


}
