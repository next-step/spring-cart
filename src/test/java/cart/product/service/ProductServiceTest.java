package cart.product.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Product;
import cart.dto.product.ProductCreateRequestDto;
import cart.dto.product.ProductResponseDto;
import cart.dto.product.ProductUpdateRequest;
import cart.repository.ProductRepository;
import cart.service.ProductService;

@SpringBootTest
@Transactional
@Sql(scripts = "classpath:schema.sql")
class ProductServiceTest {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Test
	void create() {
		//given
		String newName = "피자";
		String newUrl = "http://localhost:8080/images/pizza.png";
		Long newPrice = 40000L;

		Product product = new Product();
		product.update(newName, newUrl, newPrice);
		ProductCreateRequestDto productCreateRequestDto = ProductCreateRequestDto.fromProduct(product);
		//when
		ProductResponseDto productResponseDto = productService.create(productCreateRequestDto);

		//then
		assertEquals("피자", productResponseDto.getName());
		assertEquals("http://localhost:8080/images/pizza.png", productResponseDto.getImage());
		assertEquals(40000L, productResponseDto.getPrice());
	}

	@Test
	void update() {
		//given
		String newName = "새 피자";
		String newUrl = "http://localhost:8080/images/new_pizza.png";
		Long newPrice = 50000L;
		Long existingProductId = 1L;

		ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(newName, newUrl, newPrice,
			existingProductId);

		//when
		ProductResponseDto productResponseDto = productService.update(existingProductId, productUpdateRequest);

		//then
		assertEquals("새 피자", productResponseDto.getName());
		assertEquals("http://localhost:8080/images/new_pizza.png", productResponseDto.getImage());
		assertEquals(50000L, productResponseDto.getPrice());
	}

	@Test
	void deleteProduct() {
		//given
		Long productId = 1L;

		//when
		productService.deleteProduct(productId);

		//then
		assertFalse(productRepository.findById(productId).isPresent());
	}

	@Test
	void findAll() {
		//given
		int initialSize = productRepository.findAll().size();

		//when
		int newSize = productService.findAll().getProducts().size();

		//then
		assertEquals(initialSize, newSize);

	}
}
