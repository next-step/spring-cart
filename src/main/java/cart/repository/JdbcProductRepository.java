package cart.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import cart.domain.Product;

@Repository
public class JdbcProductRepository implements ProductRepository {
	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;

	private final RowMapper<Product> productRowMapper = (rs, rowNum) ->
		new Product(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getString("image"),
			rs.getLong("price"));

	public JdbcProductRepository(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("product")
			.usingGeneratedKeyColumns("id");
	}

	@Override
	public Product save(Product product) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
		Number key = jdbcInsert.executeAndReturnKey(parameterSource);
		product.updateId(key.longValue());
		return product;
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE product SET name = :name,image=:image,price=:price WHERE id = :id";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
		jdbcTemplate.update(sql, parameterSource);
	}

	@Override
	public Optional<Product> findById(Long id) {
		String sql = "SELECT id,name,image,price FROM product WHERE id=:id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
		List<Product> products = jdbcTemplate.query(sql, sqlParameterSource, productRowMapper);
		return products.stream().findAny();
	}

	@Override
	public List<Product> findAll() {
		String sql = "SELECT id,name,image,price FROM product";
		return jdbcTemplate.query(sql, productRowMapper);
	}

	@Override
	public void delete(Product product) {
		String sql = "DELETE FROM product WHERE id = :id";
		jdbcTemplate.update(sql, Map.of("id", product.getId()));
	}
}
