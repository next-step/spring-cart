package cart.controller.usecase;

import cart.dto.ProductInformation;
import cart.dto.ProductRegistrationData;

public interface ProductRegistrationUseCase {
    ProductInformation registerProduct(ProductRegistrationData productRegistrationData);
}
