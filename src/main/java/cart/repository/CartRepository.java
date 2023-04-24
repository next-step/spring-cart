package cart.repository;

import cart.domain.Cart;
import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addCart(Cart cart) {
        String sql = "INSERT INTO CART(member_id, product_id) VALUES(?, ?)";
        jdbcTemplate.update(
                    sql,
                    cart.getMemberId(),
                    cart.getProductId()
                );
    }

    public List<Product> getCart(Long memberId) {
        String sql = "SELECT p.id, p.name, p.image, p.price " +
                "FROM PRODUCT p " +
                "INNER JOIN CART c " +
                "ON p.id = c.product_id " +
                "WHERE c.member_id = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("Name"),
                            resultSet.getString("Image"),
                            resultSet.getInt("price")
                    );
                    return product;
                },
                memberId
        );
    }
}
