package cart.service.product;

import cart.domain.product.Product;
import cart.infrastructure.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductService productService;

    private Product insertProduct(String name, String imageUrl, int price) {
        Product givenProduct = Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();

        return productDao.insert(givenProduct);
    }

    @Test
    void findAllProducts() {
        insertProduct("nameA", "image.com/imageA", 10000);
        insertProduct("nameB", "image.com/imageB", 20000);

        List<Product> products = productService.findAllProducts();
        assertThat(products).flatExtracting(Product::getName).containsExactly("nameA", "nameB");
        assertThat(products).flatExtracting(Product::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(products).flatExtracting(Product::getPrice).containsExactly(10000, 20000);
    }
}