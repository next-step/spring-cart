package cart.data;

import cart.data.entity.CartProduct;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductJdbcRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCT")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void addProduct(CartProduct product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getProductName());
        parameters.put("price", product.getProductPrice());
        parameters.put("image_url", product.getProductImageUrl());
        parameters.put("created_at", product.getCreatedAt());
        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<CartProduct> getProducts() {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from PRODUCT";
        return jdbcTemplate.query(sql, this.cartProductMapper());
    }

    @Override
    public Optional<CartProduct> getProductById(long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select * from PRODUCT where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, this.cartProductMapper(), id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateProduct(CartProduct cartProduct) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "update PRODUCT set name = ? ,price = ? ,image_url = ? where id = ?";
        jdbcTemplate.update(sql, cartProduct.getProductName(), cartProduct.getProductPrice(),
                cartProduct.getProductImageUrl(), cartProduct.getProductId());
    }

    @Override
    public int removeProduct(long id) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "delete from PRODUCT where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private RowMapper<CartProduct> cartProductMapper() {
        return (rs, rowNum) -> {
            long productId = rs.getLong("id");
            String productName = rs.getString("name");
            int productPrice = rs.getInt("price");
            String productImageUrl = rs.getString("image_url");
            return new CartProduct(productId, productName, productPrice, productImageUrl);
        };
    }
}
