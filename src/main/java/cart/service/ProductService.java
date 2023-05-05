package cart.service;

import cart.domain.ProductEntity;
import cart.presentation.dto.ProductCreateRequest;
import cart.presentation.dto.ProductDetailResponse;
import cart.presentation.dto.ProductUpdateRequest;
import cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long registerProduct(ProductCreateRequest request) {
        ProductEntity newProduct = request.toEntity();
        return this.productRepository.save(newProduct);
    }

    public void updateProduct(Long id, ProductUpdateRequest request) {
        ProductEntity product = request.toEntity();
        product.setId(id);
        this.productRepository.update(product);
    }

    public List<ProductDetailResponse> allProducts() {
        List<ProductEntity> products = this.productRepository.findAll();
        return products.stream()
                .map(ProductDetailResponse::from)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
