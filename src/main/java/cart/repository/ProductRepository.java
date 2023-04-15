package cart.repository;

import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<Product> rowMapper = (resultSet, rowNum)
            -> new Product(resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("image"),
            resultSet.getLong("price"));

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("PRODUCT");
    }

    public List<Product> findAll() {
        return this.jdbcTemplate.query("SELECT ID, NAME, IMAGE, PRICE FROM PRODUCT", rowMapper);
    }

    public void save(Product product) {
        jdbcInsert.execute(Product.getInsertParameter(product));
    }

    public void update(Product product) {
        String updateQuery = "UPDATE PRODUCT SET NAME = ? , IMAGE = ?, PRICE = ? WHERE ID = ?";
        jdbcTemplate.update(updateQuery, product.getName(), product.getImage(), product.getPrice(), product.getId());
    }

    public void deleteById(long id) {
        String deleteQuery = "DELETE FROM PRODUCT WHERE ID = ?";
        jdbcTemplate.update(deleteQuery, id);
    }

    public Product findById(long productId) {
        String sql = "SELECT ID, NAME, IMAGE, PRICE FROM PRODUCT WHERE ID = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, productId);
    }
}