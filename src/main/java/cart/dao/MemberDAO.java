package cart.dao;

import cart.model.Member;
import cart.model.Members;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    private final RowMapper<Member> actorRowMapper = (resultSet, rowNum) -> {
        Member member = new Member(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("password")
        );
        return member;
    };
    private JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Members list() {
        String sql = "select * from member";
        Members members = new Members(jdbcTemplate.query(sql, actorRowMapper));
        return members;
    }

    public int insertMember(Member member) {
        String sql = "insert into member (name, email, password) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public Member getMember(int id) {
        String sql = "select * from member where id = ?";
        Member member = jdbcTemplate.queryForObject(sql, actorRowMapper, id);
        return member;
    }

    public int updateMember(Member member) {
        String sql = "update member set name = ?, email = ?, password = ? where id = ?";
        return this.jdbcTemplate.update(sql, member.getName(), member.getEmail(),
            member.getPassword(),
            member.getId());
    }

    public int deleteMember(int id) {
        String sql = "delete from member where id = ?";
        return this.jdbcTemplate.update(sql, id);
    }
}
