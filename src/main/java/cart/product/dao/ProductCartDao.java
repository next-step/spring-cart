package cart.product.dao;

import cart.product.domain.Cart;
import cart.product.dto.ProductCartDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductCartDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertProduct;

    public ProductCartDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertProduct = new SimpleJdbcInsert(dataSource)
                .withTableName("cart_list")
                .usingGeneratedKeyColumns("id")
                .usingColumns("email", "product_id");
    }

    public Integer insertProduct(Cart cart) {

        Map<String, Object> parameters = new HashMap<String, Object>(2);

        parameters.put("email", cart.getEmail());
        parameters.put("product_id", cart.getProductId());

        Integer cartId = insertProduct.executeAndReturnKey(parameters).intValue();

        return cartId;
    }

    public List<ProductCartDto> selectCarts(String email) {
        String sql = "select a.id, b.product_name, b.image_url, b.price from cart_list a inner join product_list b on a.product_id = b.id where a.email = ?";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    ProductCartDto productCartDto = new ProductCartDto(
                            resultSet.getInt("id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("price")
                    );
                    
                    return productCartDto;
                }, email);
    }

    public void deleteCart(Integer id) {
        String sql = "delete from cart_list where id = ?";
        jdbcTemplate.update(sql, id);
    }

}
