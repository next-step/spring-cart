package cart.application;

import cart.controller.usecase.ProductRegistrationUseCase;
import cart.dto.ProductInformation;
import cart.dto.ProductRegistrationData;
import cart.product.Product;
import cart.product.ProductEntity;
import cart.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
