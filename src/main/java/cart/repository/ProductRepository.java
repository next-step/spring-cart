package cart.repository;

import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public Long save(String name, int price, String imageUrl) {
    String query = "INSERT INTO PRODUCT(NAME, PRICE, IMAGE_URL) VALUES(?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(
        connection -> {
          PreparedStatement preparedStatement =
              connection.prepareStatement(query, new String[] {"id"});
          preparedStatement.setString(1, name);
          preparedStatement.setInt(2, price);
          preparedStatement.setString(3, imageUrl);

          return preparedStatement;
        },
        keyHolder);

    return keyHolder.getKey().longValue();
  }

  public List<Product> findAll() {
    String query =
        "SELECT P.ID AS id, P.NAME AS name, P.PRICE AS price, P.IMAGE_URL AS imageUrl FROM PRODUCT as P";
    return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Product.class));
  }
}