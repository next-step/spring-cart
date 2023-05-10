package cart.infrastructure;

import cart.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

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
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        assertThat(insertedProduct.getId()).isNotNull();
    }

    @Test
    void findById() {
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(insertedProduct.getId()).get());

        assertThat(insertedProduct).isEqualTo(foundProduct);
    }

    @Test
    void findAll() {
        insertProduct("상품A", "image.com/imageA", 10000);
        insertProduct("상품B", "image.com/imageB", 20000);

        List<Product> products = productDao.findAll();
        assertThat(products).flatExtracting(Product::getName).containsExactly("상품A", "상품B");
        assertThat(products).flatExtracting(Product::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(products).flatExtracting(Product::getPrice).containsExactly(10000, 20000);
    }

    @Test
    void update() {
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Product updateProduct = Product.builder()
                .id(insertedProduct.getId())
                .name("상품B")
                .imageUrl("image.com/imageB")
                .price(20000)
                .build();

        Long updatedProductId = productDao.update(updateProduct);
        assertThat(insertedProduct.getId()).isEqualTo(updatedProductId);

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(updatedProductId).get());

        assertThat(foundProduct.getName()).isEqualTo(updateProduct.getName());
        assertThat(foundProduct.getImageUrl()).isEqualTo(updateProduct.getImageUrl());
        assertThat(foundProduct.getPrice()).isEqualTo(updateProduct.getPrice());
    }

    @Test
    void delete() {
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        productDao.delete(insertedProduct);

        assertThatThrownBy(() -> productDao.findById(insertedProduct.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}