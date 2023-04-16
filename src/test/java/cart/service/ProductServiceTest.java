package cart.service;

import cart.controller.response.ProductResponse;
import cart.domain.Product;
import cart.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void save() {
        productService.save(new Product(100, "saveTest", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000));
        List<ProductResponse> actual = productService.findAllProducts();
        ProductResponse expect = new ProductResponse(100, "saveTest", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000);
        Assertions.assertThat(actual).contains(expect);
    }

    @Test
    public void update() {
        productService.update(new Product(1, "TEST", "TEST", 25000));
        List<ProductResponse> allProducts = productService.findAllProducts();
        ProductResponse expect = new ProductResponse(1, "TEST", "TEST", 25000);
        Assertions.assertThat(allProducts).contains(expect);
    }

    @Test
    public void delete() {
        ProductResponse productResponse = ProductResponse.extract(productRepository.findById(1));

        productService.delete(1);
        List<ProductResponse> actual = productService.findAllProducts();
        Assertions.assertThat(actual).doesNotContain(productResponse);
    }
}