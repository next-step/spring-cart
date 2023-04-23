package cart.dao;

import cart.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertProduct;

    public ProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertProduct = new SimpleJdbcInsert(dataSource)
                .withTableName("product_items")
                .usingGeneratedKeyColumns("id")
                .usingColumns("name", "image_url", "price");
    }

    public Product insertProduct(Product product) {
        Map<String, Object> parameters = new HashMap<String, Object>(3);

        parameters.put("name", product.getName());
        parameters.put("image_url", product.getImageUrl());
        parameters.put("price", product.getPrice());

        int productId = insertProduct.executeAndReturnKey(parameters).intValue();

        return new Product(productId, product.getName(), product.getImageUrl(), product.getPrice());
    }

    public List<Product> selectProducts() {
        String sql = "select id,name,image_url,price,created_at from product_items";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Product prodouct = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("image_url"),
                            resultSet.getInt("price")
                    );
                    return prodouct;
                });
    }

    public Product selectOneProduct(int id) {
        String sql = "select id,name, image_url,price from product_items where id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> {
                        Product product = new Product(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("image_url"),
                                resultSet.getInt("price")
                        );
                        return product;
                    }, id);
        } catch (EmptyResultDataAccessException e) {return null;}
    }

    public void updateProduct(Product insertProduct) {
        String sql = "update product_items set name=?, image_url = ?, price = ? where id = ?";
        jdbcTemplate.update(sql, insertProduct.getName(), insertProduct.getImageUrl(), insertProduct.getPrice(), insertProduct.getId());
    }

    public void deleteProdect(int id) {
        String sql = "delete from product_items where id = ?";
        jdbcTemplate.update(sql, id);
    }


}
