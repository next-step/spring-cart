package cart.data;

import cart.data.entity.CartProduct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ProductJdbcRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("CART_PRODUCT");
    }

    @Override
    public void addProduct(CartProduct product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product_name", product.getProductName());
        parameters.put("product_price", product.getProductPrice());
        parameters.put("product_image_url", product.getProductImageUrl());
        parameters.put("created_at", product.getCreatedAt());
        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<CartProduct> getProducts() {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from CART_PRODUCT";
        return jdbcTemplate.query(sql, this.cartProductMapper());
    }

    @Override
    public Optional<CartProduct> getProductById(long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from CART_PRODUCT where product_id = ?";
        CartProduct cartProduct = null;
        try {
            cartProduct = jdbcTemplate.queryForObject(sql, this.cartProductMapper(), id);
        } catch (Exception e) {
            logger.info("sql 오류 -> {}", e.getMessage());
        }
        return Optional.ofNullable(cartProduct);
    }

    @Override
    public void updateProduct(CartProduct cartProduct) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "update CART_PRODUCT set " +
                "product_name = ? ,product_price = ? ,product_image_url = ? " +
                "where product_id = ?";
        jdbcTemplate.update(sql, cartProduct.getProductName(), cartProduct.getProductPrice(),
                cartProduct.getProductImageUrl(), cartProduct.getProductId());
    }

    @Override
    public void removeProduct(long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "delete from CART_PRODUCT where product_id = ?";
        int updateCount = jdbcTemplate.update(sql, id);
        if (updateCount != 1) {
            throw new RuntimeException(id + "에 해당하는 상품을 삭제하는데 오류가 발생하였습니다.");
        }
    }

    private RowMapper<CartProduct> cartProductMapper() {
        return (rs, rowNum) -> {
            long productId = rs.getLong("product_id");
            String productName = rs.getString("product_name");
            int productPrice = rs.getInt("product_price");
            String productImageUrl = rs.getString("product_image_url");
            return new CartProduct(productId, productName, productPrice, productImageUrl);
        };
    }
}
