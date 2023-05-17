package cart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Product;
import cart.dto.product.ProductCreateRequestDto;
import cart.dto.product.ProductResponseDto;
import cart.dto.product.ProductUpdateRequest;
import cart.dto.product.ProductsResponseDto;
import cart.exception.ErrorType;
import cart.exception.ServiceException;
import cart.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public ProductResponseDto create(ProductRequestDto productRequestDto) {
		Product product = productRequestDto.toProduct();
		return ProductResponseDto.from(productRepository.save(product));
	}

	@Transactional
	public ProductResponseDto update(Long id, ProductUpdateRequest productUpdateRequest) {
		Product product = findProduct(id);
		product.setName(productUpdateRequest.getName());
		product.setPrice(productUpdateRequest.getPrice());
		product.setImage(productUpdateRequest.getImage());
		productRepository.update(product);
		return ProductResponseDto.from(product);
	}

	private Product findProduct(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ServiceException(ErrorType.PRODUCT_NOT_FOUND));
	}

	@Transactional
	public void deleteProduct(Long id) {
		Product product = findProduct(id);
		productRepository.delete(product);
	}

	public ProductsResponseDto findAll() {
		return ProductsResponseDto.from(productRepository.findAll());
	}

}
