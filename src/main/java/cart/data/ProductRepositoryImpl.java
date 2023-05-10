package cart.data;

import cart.data.entity.CartProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepositoryImpl(DataSource dataSource) {
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
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long productId = rs.getLong("product_id");
            String productName = rs.getString("product_name");
            int productPrice = rs.getInt("product_price");
            String productImageUrl = rs.getString("product_image_url");
            return new CartProduct(productId, productName, productPrice, productImageUrl);
        });
    }

    @Override
    public CartProduct getProductById(long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from CART_PRODUCT where product_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long productId = rs.getLong("product_id");
            String productName = rs.getString("product_name");
            int productPrice = rs.getInt("product_price");
            String productImageUrl = rs.getString("product_image_url");
            return new CartProduct(productId, productName, productPrice, productImageUrl);
        }, id);
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
        jdbcTemplate.update(sql, id);
    }
}
