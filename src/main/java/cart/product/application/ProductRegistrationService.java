package cart.product.application;

import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductRegistrationData;
import cart.product.application.usecase.ProductRegistrationUseCase;
import cart.product.domain.Product;
import cart.product.domain.ProductEntity;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductRegistrationService implements ProductRegistrationUseCase {

    private final ProductRepository productRepository;

    public ProductRegistrationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductInformation registerProduct(ProductRegistrationData productRegistrationData) {
        Product product = new Product(
                productRegistrationData.getName(),
                productRegistrationData.getImage(),
                productRegistrationData.getPrice()
        );
        ProductEntity productEntity = productRepository.save(product);
        return ProductInformation.fromProductEntity(productEntity);
    }
}
