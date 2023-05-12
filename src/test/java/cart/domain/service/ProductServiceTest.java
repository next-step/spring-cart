package cart.domain.service;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import cart.testdouble.InMemoryProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductServiceTest {

    private ProductRepository productRepository = new InMemoryProductRepository();
    private ProductService productService = new ProductService(productRepository);

    @Test
    void getAll() {
        Product product1 = new Product(1L, "a", 1000, "http://a.com");
        Product product2 = new Product(2L, "b", 2000, "http://b.com");
        productRepository.insert(product1);
        productRepository.insert(product2);

        Collection<Product> products = productService.getAll();

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void save() {
        Product product1 = new Product(1L, "a", 1000, "http://a.com");
        Product product2 = new Product(2L, "b", 2000, "http://b.com");
        productService.save(product1);
        productService.save(product2);

        Collection<Product> products = productRepository.findAll();

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void update() {
        Product product = new Product(1L, "a", 1000, "http://a.com");
        productRepository.insert(product);

        productService.update(1L, new Product(1L, "b", 2000, "http://b.com"));

        Product updated = productRepository.findById(1L);
        assertThat(updated.getName()).isEqualTo("b");
        assertThat(updated.getPrice()).isEqualTo(2000);
        assertThat(updated.getImageUrl()).isEqualTo("http://b.com");
    }

    @Test
    void delete() {
        Product product = new Product(1L, "a", 1000, "http://a.com");
        productRepository.insert(product);

        productService.delete(1L);

        Collection<Product> products = productRepository.findAll();
        assertThat(products.size()).isEqualTo(0);
    }
}
