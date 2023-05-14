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
                .withTableName("CART_ITEM");
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

        String sql = "select b.item_id as id, \n" +
                "       p.product_image_url as imageUrl, \n" +
                "       p.product_name as name, \n" +
                "       p.product_price as price\n" +
                "from CART_ITEM b \n" +
                "inner join CART_MEMBER m \n" +
                "on b.member_id = m.member_id \n" +
                "inner join CART_PRODUCT p \n" +
                "on b.product_id = p.product_id \n" +
                "where b.member_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String imageUrl = rs.getString("imageUrl");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            return new ViewCartItem(id, imageUrl, name, price);
        }, memberId);
    }

    @Override
    public void removeFromCart(Long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "delete from CART_ITEM where item_id = ?";
        int updateCount = jdbcTemplate.update(sql, id);
        if (updateCount != 1) {
            throw new RuntimeException(id + "에 해당하는 장바구니 아이탬을 삭제하는데 오류가 발생하였습니다.");
        }
    }
}
