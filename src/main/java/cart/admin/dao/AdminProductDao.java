package cart.admin.dao;

import cart.admin.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminProductDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertProduct;


    public AdminProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertProduct = new SimpleJdbcInsert(dataSource)
                .withTableName("product_list")
                .usingGeneratedKeyColumns("product_id")
                .usingColumns("product_name","product_path","product_price");
    }

    public Product insertProduct(Product product){

        Map<String, Object> parameters = new HashMap<String, Object>(3);

        parameters.put("product_name", product.getProductName());
        parameters.put("product_path", product.getProductPath());
        parameters.put("product_price",product.getProductPrice());

        int productId = insertProduct.executeAndReturnKey(parameters).intValue();

        return new Product(productId, product.getProductName(), product.getProductPath(), product.getProductPrice());
    }

    /*
        product_id INT      NOT NULL AUTO_INCREMENT,
        product_name varchar(200)      NOT NULL,
        product_path varchar(200)      NOT NULL,
        product_price INT              NOT NULL,
        created_at DATETIME NOT NULL default current_timestamp,
    */

    public List<Product> selectProducts() {
        String sql = "select product_id,product_name,product_path,product_price,created_at from product_list";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Product prodouct = new Product(
                            resultSet.getInt( "product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_path"),
                            resultSet.getInt("product_price")
                    );
                    return prodouct;
                });
    }

    public Product selectOneProduct( int productId ) {
        String sql = "select product_id,product_name,product_path,product_price from product_list where product_id = ?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> {
                        Product product = new Product(
                                resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getString("product_path"),
                                resultSet.getInt("product_price")
                        );
                        return product;
                    }, productId);
        }catch (EmptyResultDataAccessException e){

            return null;
        }

    }

    public void updateProduct(Product insertProduct) {
        String sql = "update product_list set product_name=?, product_path = ?, product_price = ? where product_id = ?";
        jdbcTemplate.update(sql, insertProduct.getProductName(), insertProduct.getProductPath(), insertProduct.getProductPrice(), insertProduct.getProductId());
    }

    public void deleteProdect(int productId ) {
        String sql = "delete from product_list where product_id = ?";
        jdbcTemplate.update(sql, productId);
    }
}
