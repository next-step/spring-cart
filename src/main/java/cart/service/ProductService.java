package cart.service;

import cart.presentation.dto.ProductDetailResponse;
import cart.repository.ProductEntity;
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

    public List<ProductDetailResponse> allProducts() {
        List<ProductEntity> products = this.productRepository.findAll();
        return products.stream()
                .map(ProductDetailResponse::from)
                .collect(Collectors.toList());
    }
}
