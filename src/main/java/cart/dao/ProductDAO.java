package cart.dao;

import cart.model.Product;
import cart.model.Products;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {

    private final RowMapper<Product> actorRowMapper = (resultSet, rowNum) -> {
        Product product = new Product(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("image"),
            resultSet.getInt("price")
        );
        return product;
    };
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Products list() {
        String sql = "select * from product";
        Products products = new Products(jdbcTemplate.query(sql, actorRowMapper));
        return products;
    }

    public int insertProduct(Product product) {
        String sql = "insert into product (name,image,price) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getName());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public Product getProduct(int id) {
        String sql = "select * from product where id = ?";
        Product product = jdbcTemplate.queryForObject(sql, actorRowMapper, id);
        return product;
    }

    public int updateProduct(Product product) {
        String sql = "update product set name = ?, image = ?, price = ? where id = ?";
        return this.jdbcTemplate.update(sql, product.getName(), product.getImage(),
            product.getPrice(),
            product.getId());
    }

    public int deleteProduct(int id) {
        String sql = "delete from product where id = ?";
        return this.jdbcTemplate.update(sql, id);
    }
}
