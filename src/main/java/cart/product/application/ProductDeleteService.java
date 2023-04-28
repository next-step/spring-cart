package cart.product.application;

import cart.product.application.usecase.ProductDeleteUseCase;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDeleteService implements ProductDeleteUseCase {

    private final ProductRepository productRepository;

    public ProductDeleteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
