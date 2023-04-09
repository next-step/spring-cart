package cart.dao;

import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SpringCartDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertProduct;


    public SpringCartDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertProduct = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCT")
                .usingGeneratedKeyColumns("id");
    }

    public void insertProduct(String name, String imageUrl, int price) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("image_url", imageUrl);
        parameters.put("price", price);
        parameters.put("created_at", LocalDateTime.now());
        insertProduct.execute(parameters);
    }

    public void deleteProduct(int id) {
        String sql = "delete from PRODUCT where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateProduct(Product product) {
        String sql = "update PRODUCT set name = ?" + ", price = ?" + ", image_url = ?" + "   where id = ? ";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public List<Product> selectProduct() {
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
