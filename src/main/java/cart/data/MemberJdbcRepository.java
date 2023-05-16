package cart.data;

import cart.data.entity.CartMember;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJdbcRepository implements MemberRepository {

    private SimpleJdbcInsert simpleJdbcInsert;

    public MemberJdbcRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("MEMBER");
    }

    @Override
    public List<CartMember> getMembers() {
        String sql = "select * from MEMBER";
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();

        return jdbcTemplate.query(sql, carMemberRowMapper());
    }

    @Override
    public Optional<CartMember> getMember(String email, String password) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from MEMBER where email = ? and password = ?";
        CartMember cartMember;
        try {
            cartMember = jdbcTemplate.queryForObject(sql, carMemberRowMapper(), email, password);
        } catch (Exception e) {
            return Optional.empty();
        };
        return Optional.ofNullable(cartMember);
    }

    private RowMapper<CartMember> carMemberRowMapper() {
        return (rs, rowNum) -> {
            long memberId = rs.getLong("id");
            String memberEmail = rs.getString("email");
            String memberPassword = rs.getString("password");
            return new CartMember(memberId, memberEmail, memberPassword);
        };
    }
}
