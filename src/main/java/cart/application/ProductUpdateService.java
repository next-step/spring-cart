package cart.application;

import cart.controller.usecase.ProductUpdateUseCase;
import cart.dto.ProductInformation;
import cart.dto.ProductUpdateData;
import cart.product.Product;
import cart.product.ProductEntity;
import cart.product.ProductRepository;
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
