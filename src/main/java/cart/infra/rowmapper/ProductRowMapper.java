package cart.infra.rowmapper;

import cart.domain.Money;
import cart.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        long price = rs.getLong("price");
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .image(rs.getString("image"))
                .price(Money.of(price))
                .build();
    }

}
