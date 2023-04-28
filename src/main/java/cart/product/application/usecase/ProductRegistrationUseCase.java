package cart.product.application.usecase;

import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductRegistrationData;

public interface ProductRegistrationUseCase {
    ProductInformation registerProduct(ProductRegistrationData productRegistrationData);
}
