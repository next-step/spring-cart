package cart.infra;

import cart.domain.Product;
import cart.domain.Products;
import cart.exception.NotExistElementException;
import cart.infra.rowmapper.ProductRowMapper;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao extends NamedParameterJdbcDaoSupport {

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

    public void create(Product product) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(product);

        int key = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        product.setId(key);
    }

    public Products findAllProducts() {
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Product> products = getNamedParameterJdbcTemplate()
                .query(query, ROW_MAPPER);

        return new Products(products);
    }

    public void update(Product product, int id) {
        final String query = String.format("UPDATE %s " +
                "SET name = :name, image = :image, price = :price " +
                "WHERE id = :id", TABLE_NAME);
        SqlParameterSource params = new BeanPropertySqlParameterSource(product);
        getNamedParameterJdbcTemplate().update(query, params);

    }

    public Product findById(int id) {
        final String query = String.format("SELECT * FROM %s WHERE id = :id", TABLE_NAME);
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        try {
            Product product = getNamedParameterJdbcTemplate()
                    .queryForObject(query, params, ROW_MAPPER);
            return product;
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new NotExistElementException("해당 id에 해당하는 상품이 없습니다.");
        }


    }


}
