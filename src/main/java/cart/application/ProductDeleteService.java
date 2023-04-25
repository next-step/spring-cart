package cart.application;

import cart.controller.usecase.ProductDeleteUseCase;
import cart.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
