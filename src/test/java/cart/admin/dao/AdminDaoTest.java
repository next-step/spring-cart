package cart.admin.dao;

import cart.admin.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdminDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminProductDao adminProductDao;

    @BeforeEach
    void setting(){
        jdbcTemplate.execute("delete from product_list");
    }

    @Test
    void productInsert() {
        Product product = new Product("productName", "productPath", 1000);

        int productId = adminProductDao.insertProduct(product).getId();

        assertThat(productId);
    }

    @Test
    void productSelectTest() {

        Product product = new Product("vincent", "kakaopaysec", 1000);
        Product product2 = new Product("lucas", "test", 1000);

        adminProductDao.insertProduct(product);
        adminProductDao.insertProduct(product2);

        List<Product> products = adminProductDao.selectProducts();
        assertThat(products).
                hasSize(2);
    }

    @Test
    void productSelectOneTest() {

        Product product = new Product("vincent", "kakaopaysec", 1000);
        int productId = adminProductDao.insertProduct(product).getId();

        Product selectProduct= adminProductDao.selectOneProduct(productId);
        assertThat(selectProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    void productUpdateTest() {

        Product product = new Product("vincent", "kakaopaysec", 1000);
        Product insertProduct = adminProductDao.insertProduct(product);

        insertProduct.setName("lucas");
        adminProductDao.updateProduct(insertProduct);

        Product selectProducts = adminProductDao.selectOneProduct(insertProduct.getId());
        assertThat(selectProducts.getName()).isEqualTo(insertProduct.getName());
    }

    @Test
    void deleteProductTest() {

        Product product = new Product("vincent", "kakaopaysec", 1000);
        int productId = adminProductDao.insertProduct(product).getId();

        adminProductDao.deleteProdect(productId);

        Product selectProduct= adminProductDao.selectOneProduct(productId);
        assertThat(selectProduct).isNull();
    }
}
