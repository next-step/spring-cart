package cart.admin.dao;

import cart.admin.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdminDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminProductDao adminProductDao;

    List<Product> products;

    @BeforeEach
    void setting() {
        jdbcTemplate.execute("delete from product_list");
        products = Arrays.asList(
                new Product("productName", "productPath", 1000)
                , new Product("lucas", "test", 1000)
        );
    }

    @DisplayName("adminProductData 상품 INSERT 테스트")
    @Test
    void productInsertTest() {
        Product product = products.get(0);

        int productId = adminProductDao.insertProduct(product).getId();

        assertThat(productId);
    }

    @DisplayName("adminProductData 상품이름은 INSERT시 200자가 넘으면 에러 발생 테스트")
    @Test
    void productInsertErrorTest() {
        Product product = products.get(0);

        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> {
                    // when
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < 200; i++) {
                        sb.append(i);
                    }

                    product.setName(sb.toString());

                    int productId = adminProductDao.insertProduct(product).getId();
                }).withMessageContaining("Value too long for column");

    }

    @DisplayName("adminProductData 상품 다건 SELECT 테스트")
    @Test
    void productSelectTest() {

        products.stream()
                .forEach(product -> adminProductDao.insertProduct(product));

        List<Product> products = adminProductDao.selectProducts();
        assertThat(products).
                hasSize(2);
    }

    @DisplayName("adminProductData 상품 단건 SELECT 테스트")
    @Test
    void productSelectOneTest() {

        Product product = products.get(0);
        Integer productId = adminProductDao.insertProduct(product).getId();

        Product selectProduct = adminProductDao.selectOneProduct(productId);
        assertThat(selectProduct.getName()).isEqualTo(product.getName());
    }

    @DisplayName("adminProductData 상품 UPDATE 테스트")
    @Test
    void productUpdateTest() {

        Product product = products.get(0);
        Product insertProduct = adminProductDao.insertProduct(product);

        insertProduct.setName("lucas");
        adminProductDao.updateProduct(insertProduct);

        Product selectProducts = adminProductDao.selectOneProduct(insertProduct.getId());
        assertThat(selectProducts.getName()).isEqualTo(insertProduct.getName());
    }

    @DisplayName("adminProductData 상품 DELETE 테스트")
    @Test
    void deleteProductTest() {

        Product product = products.get(0);
        int productId = adminProductDao.insertProduct(product).getId();

        adminProductDao.deleteProdect(productId);

        Product selectProduct = adminProductDao.selectOneProduct(productId);
        assertThat(selectProduct).isNull();
    }
}
