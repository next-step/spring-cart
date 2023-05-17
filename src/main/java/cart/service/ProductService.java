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
	public ProductResponseDto create(ProductCreateRequestDto productCreateRequestDto) {
		Product product = productCreateRequestDto.toProduct();
		return ProductResponseDto.from(productRepository.save(product));
	}

	@Transactional
	public ProductResponseDto update(Long id, ProductUpdateRequest productUpdateRequest) {
		Product product = findProduct(id);
		String newName = productUpdateRequest.getName();
		Long newPrice = productUpdateRequest.getPrice();
		String newImage = productUpdateRequest.getImage();
		product.update(newName, newImage, newPrice);
		productRepository.update(product);
		return ProductResponseDto.from(product);
	}

	private Product findProduct(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ServiceException(ErrorType.ACCESS_DENIED));
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
