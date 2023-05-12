package cart.product.infra;

import cart.product.domain.Product;
import cart.product.domain.ProductRepository;
import cart.product.domain.Products;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao extends NamedParameterJdbcDaoSupport implements ProductRepository {

    private static final String TABLE_NAME = "product";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"name", "image", "price"};
    private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductDao(DataSource dataSource) {
        setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public Products findAll() {
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Product> products = getNamedParameterJdbcTemplate()
                .query(query, ROW_MAPPER);

        return new Products(products);
    }

    @Override
    public void save(Product product) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
        Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        product.updateId(key.longValue());
    }

    @Override
    public void update(Product product) {
        final String query = String.format("UPDATE %s SET name = :name, image = :image, price = :price " +
                "WHERE id = :id", TABLE_NAME);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(product);

        getNamedParameterJdbcTemplate().update(query, namedParameters);
    }

    @Override
    public Optional<Product> findById(long id) {
        final String query = String.format("SELECT * FROM %s WHERE id = :id", TABLE_NAME);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);

        Product product = getNamedParameterJdbcTemplate()
                .queryForObject(query, namedParameters, ROW_MAPPER);

        return Optional.ofNullable(product);
    }

    @Override
    public void delete(Product product) {
        final String query = String.format("DELETE FROM %s WHERE id = :id", TABLE_NAME);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", product.getId());

        getNamedParameterJdbcTemplate().update(query, namedParameters);
    }

    @Override
    public Products findByIds(List<Long> productIds) {
        final String query = String.format("SELECT * FROM %s WHERE id IN (:ids)", TABLE_NAME);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ids", productIds);
        List<Product> products = getNamedParameterJdbcTemplate().query(query, namedParameters, ROW_MAPPER);

        return new Products(products);
    }

}
