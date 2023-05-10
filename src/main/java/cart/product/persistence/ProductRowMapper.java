package cart.product.persistence;

import cart.product.domain.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .image(rs.getString("image"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
