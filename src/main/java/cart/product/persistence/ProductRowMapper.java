package cart.product.persistence;

import cart.product.domain.entity.Product;
import cart.product.domain.vo.ImagePath;
import cart.product.domain.vo.Price;
import cart.product.domain.vo.ProductName;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(new ProductName(rs.getString("name")))
                .price(new Price(rs.getInt("price")))
                .image(new ImagePath(rs.getString("image")))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
