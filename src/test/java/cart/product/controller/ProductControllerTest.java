package cart.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import cart.controller.ProductController;
import cart.domain.Product;
import cart.dto.product.ProductCreateRequestDto;
import cart.dto.product.ProductUpdateRequest;
import cart.repository.ProductRepository;
import cart.service.ProductService;

@SpringBootTest
@Transactional
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProductControllerTest {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	private MockMvc mockMvc;

	@BeforeEach
	void clean() {
		mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
	}

	@Test
	void createProduct() throws Exception {
		//given
		String newName = "피자";
		String newUrl = "http://localhost:8080/images/pizza.png";
		Long newPrice = 40000L;

		Product product = new Product();
		product.update(newName, newUrl, newPrice);
		ProductCreateRequestDto productCreateRequestDto = ProductCreateRequestDto.fromProduct(product);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestDtoJson = objectMapper.writeValueAsString(productCreateRequestDto);

		//when,then
		mockMvc.perform(post("/products/")
				.content(requestDtoJson)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("피자"))
			.andExpect(jsonPath("$.image").value("http://localhost:8080/images/pizza.png"))
			.andExpect(jsonPath("$.price").value(40000L))
			.andDo(print());
	}

	@Test
	void updateProduct() throws Exception {
		//given
		String updatedName = "새 피자";
		String updatedUrl = "http://localhost:8080/images/new_pizza.png";
		Long updatedPrice = 50000L;
		Long existingProductId = 1L; // This should be an ID of a product that exists before test runs.

		ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(updatedName, updatedUrl, updatedPrice,
			existingProductId);

		ObjectMapper objectMapper = new ObjectMapper();
		String requestDtoJson = objectMapper.writeValueAsString(productUpdateRequest);

		//when,then
		mockMvc.perform(put("/products/{id}", existingProductId)
				.content(requestDtoJson)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(updatedName))
			.andExpect(jsonPath("$.image").value(updatedUrl))
			.andExpect(jsonPath("$.price").value(updatedPrice))
			.andDo(print());
	}

	@Test
	void findProduct() throws Exception {
		mockMvc.perform(get("/products/")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void deleteProduct() throws Exception {
		mockMvc.perform(delete("/products/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent())
			.andDo(print());
	}
}
