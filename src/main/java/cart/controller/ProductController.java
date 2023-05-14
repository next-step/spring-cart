package cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.dto.ProductRequestDto;
import cart.dto.ProductResponseDto;
import cart.dto.ProductUpdateRequest;
import cart.dto.ProductsResponseDto;
import cart.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponseDto = productService.create(productRequestDto);
		return ResponseEntity.ok(productResponseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
		@RequestBody ProductUpdateRequest productUpdateRequest) {
		ProductResponseDto productResponseDto = productService.update(id, productUpdateRequest);
		return ResponseEntity.ok(productResponseDto);
	}

	@GetMapping
	public ResponseEntity<ProductsResponseDto> findProduct() {
		ProductsResponseDto productsResponseDto = productService.findAll();
		return ResponseEntity.ok(productsResponseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
