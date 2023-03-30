package cart.dao;

import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//
    public int insertProduct(Product product) {
        String sql = "insert into PRODUCT (product_name, product_price,product_imagename) values (?, ?, ?)";
        return jdbcTemplate.update(sql,product.getName() ,product.getPrice() ,product.getImagename() );
    }
    public int deleteProduct(Product product) {
        String sql = "delete from PRODUCT where product_id = ?";
        return jdbcTemplate.update(sql,product.getId() );
    }

    public int updateProduct(Product product) {
        String sql = "update PRODUCT set product_name = ?" + ", product_price = ?" + ",product_imagename = ?" + "   where product_id = ? ";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImagename(), product.getId());
    }
    public List<Product> selectProducts() {
        String sql = "SELECT product_id ,product_name , product_price , product_imagename   FROM PRODUCT";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getString("product_imagename")
                    );
                    return product;
                });
    }
}
