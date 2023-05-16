package cart.product.persistence;

import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao implements ProductRepository {
    private final SimpleJdbcInsert insertActor;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public ProductDao(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCT")
                .usingGeneratedKeyColumns("id");
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product insert(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("image", product.getImage());
        parameters.put("created_at", LocalDateTime.now());

        Number number = insertActor.executeAndReturnKey(parameters);
        product.setId(number.longValue());
        return product;
    }

    @Override
    public Product findById(Long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from product where id = :id",
                    parameters,
                    new ProductRowMapper()
            );
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
        }
    }

    @Override
    public List<Product> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        return namedParameterJdbcTemplate.query(
                "select * from product",
                parameters,
                new ProductRowMapper()
        );
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("image", product.getImage());
        parameters.put("price", product.getPrice());
        parameters.put("id", id);
        namedParameterJdbcTemplate.update(
                "update product " +
                        " set name = :name" +
                        " ,image = :image" +
                        " ,price = :price" +
                        " where id = :id",
                parameters
        );
        product.setId(id);
        return product;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameters
                = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(
                "delete from product where id = :id",
                parameters);
    }
}
