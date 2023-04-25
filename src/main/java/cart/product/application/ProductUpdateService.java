package cart.product.application;

import cart.product.application.usecase.ProductUpdateUseCase;
import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductUpdateData;
import cart.product.domain.Product;
import cart.product.domain.ProductEntity;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductRepository productRepository;

    public ProductUpdateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductInformation updateProduct(Long productId, ProductUpdateData productUpdateData) {
        ProductEntity productEntity = productRepository.findById(productId);
        Product product = new Product(
                productUpdateData.getName(),
                productUpdateData.getImage(),
                productUpdateData.getPrice()
        );
        ProductEntity newProductEntity = productEntity.update(product);
        ProductEntity updatedProductEntity = productRepository.update(newProductEntity);
        return ProductInformation.fromProductEntity(updatedProductEntity);
    }
}
