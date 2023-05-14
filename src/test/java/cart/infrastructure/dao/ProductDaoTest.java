package cart.infrastructure.dao;

import cart.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@JdbcTest
class ProductDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDao(jdbcTemplate, dataSource);
    }

    @Test
    void insert() {
        // given, when
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        // then
        assertThat(insertedProduct.getId()).isNotNull();
    }

    @Test
    void findById() {
        // given
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        // when
        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(insertedProduct.getId()).get());

        // then
        assertThat(insertedProduct.getName()).isEqualTo(foundProduct.getName());
        assertThat(insertedProduct.getImageUrl()).isEqualTo(foundProduct.getImageUrl());
        assertThat(insertedProduct.getPrice()).isEqualTo(foundProduct.getPrice());
    }

    @Test
    void findAll() {
        // given
        insertProduct("상품A", "image.com/imageA", 10000);
        insertProduct("상품B", "image.com/imageB", 20000);

        // when
        List<Product> products = productDao.findAll();

        // then
        assertThat(products).flatExtracting(Product::getName).containsExactly("상품A", "상품B");
        assertThat(products).flatExtracting(Product::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(products).flatExtracting(Product::getPrice).containsExactly(10000, 20000);
    }

    @Test
    void update() {
        // given
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Product updateProduct = Product.builder()
                .id(insertedProduct.getId())
                .name("상품B")
                .imageUrl("image.com/imageB")
                .price(20000)
                .build();

        // when
        Long updatedProductId = productDao.update(updateProduct);

        // then
        assertThat(insertedProduct.getId()).isEqualTo(updatedProductId);

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(updatedProductId).get());
        assertThat(foundProduct.getName()).isEqualTo(updateProduct.getName());
        assertThat(foundProduct.getImageUrl()).isEqualTo(updateProduct.getImageUrl());
        assertThat(foundProduct.getPrice()).isEqualTo(updateProduct.getPrice());
    }

    @Test
    void delete() {
        // given
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        // when
        productDao.delete(insertedProduct);

        // then
        assertThatThrownBy(() -> productDao.findById(insertedProduct.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Product insertProduct(String name, String imageUrl, int price) {
        Product givenProduct = Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();

        return productDao.insert(givenProduct);
    }
}
