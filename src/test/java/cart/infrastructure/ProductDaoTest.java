package cart.infrastructure;

import cart.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@JdbcTest
class ProductDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDao(jdbcTemplate);
    }

    private Product insertProduct(String name, String imageUrl, int price) {
        Product givenProduct = Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();

        return productDao.insert(givenProduct);
    }

    @Test
    void insert() {
        Product insertedProduct = insertProduct("name", "image.com/image", 10000);
        assertThat(insertedProduct.getId()).isNotNull();
    }

    @Test
    void findById() {
        Product insertedProduct = insertProduct("name", "image.com/image", 10000);
        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(insertedProduct.getId()).get());

        assertThat(insertedProduct).isEqualTo(foundProduct);
    }

    @Test
    void findAll() {
        insertProduct("nameA", "image.com/imageA", 10000);
        insertProduct("nameB", "image.com/imageB", 20000);

        List<Product> products = productDao.findAll();
        assertThat(products).flatExtracting(Product::getName).containsExactly("nameA", "nameB");
        assertThat(products).flatExtracting(Product::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(products).flatExtracting(Product::getPrice).containsExactly(10000, 20000);
    }
}