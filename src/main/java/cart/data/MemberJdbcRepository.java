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
                .withTableName("CART_MEMBER");
    }

    @Override
    public List<CartMember> getMembers() {
        String sql = "select * from CART_MEMBER";
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();

        return jdbcTemplate.query(sql, carMemberRowMapper());
    }

    @Override
    public Optional<CartMember> getMember(String email, String password) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * " +
                "from CART_MEMBER " +
                "where member_email = ? and member_password = ?";
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
            long memberId = rs.getLong("member_id");
            String memberEmail = rs.getString("member_email");
            String memberPassword = rs.getString("member_password");
            return new CartMember(memberId, memberEmail, memberPassword);
        };
    }
}
