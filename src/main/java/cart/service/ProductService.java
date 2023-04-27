package cart.service;

import cart.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    final private JdbcTemplate jdbcTemplate;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Product> getProductList() {
        String sql = "SELECT * FROM products";
        List<Product> productList  = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("price")
                    );
                    return product;
                });
        return productList;
    }

    public Product addProduct(Product product) {
        String sql = "INSERT INTO products (name, image_url, price) VALUES (?,?,?)";
        int result = jdbcTemplate.update(sql, product.getName(), product.getImageUrl(), product.getPrice());
        if (result > 0) {
            return product;
        }
        return null;
    }

    public Product updateProduct(String id, Product product) {
        String sql = "UPDATE products SET name=?, image_url=?, price=? WHERE id=?";
        int result = jdbcTemplate.update(sql, product.getName(), product.getImageUrl(), product.getPrice(), id);
        if (result > 0) {
            return product;
        }
        return null;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE from products WHERE id=?";
        int result = jdbcTemplate.update(sql, id);
        if (result > 0) {
            return true;
        }
        return false;
    }

}
