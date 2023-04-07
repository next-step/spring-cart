package cart.dao;

import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao {
    private JdbcTemplate jdbcTemplate;

    public CartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> selectProductList() {
        String sql = "SELECT id , name , image_url, price   FROM PRODUCT";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("image_url")

                    );
                    return product;
                });
    }
}
