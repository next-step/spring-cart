package cart.data;

import cart.data.entity.CartItem;
import cart.presentation.dto.ViewCartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemJdbcRepository implements ItemRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public ItemJdbcRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("CART_ITEM")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void addToCart(CartItem item) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", item.getMemberId());
        parameters.put("product_id", item.getProductId());
        parameters.put("created_at", item.getCreatedAt());
        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<ViewCartItem> getCartItems(Long memberId) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();

        String sql = "select i.id as id, \n" +
                "       p.image_url as imageUrl, \n" +
                "       p.name as name, \n" +
                "       p.price as price\n" +
                "from CART_ITEM i \n" +
                "inner join MEMBER m \n" +
                "on i.member_id = m.id \n" +
                "inner join PRODUCT p \n" +
                "on i.product_id = p.id \n" +
                "where i.member_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String imageUrl = rs.getString("imageUrl");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            return new ViewCartItem(id, imageUrl, name, price);
        }, memberId);
    }

    @Override
    public int removeFromCart(Long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "delete from CART_ITEM where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
