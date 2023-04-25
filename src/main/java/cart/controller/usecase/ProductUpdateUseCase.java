package cart.controller.usecase;

import cart.dto.ProductInformation;
import cart.dto.ProductUpdateData;

public interface ProductUpdateUseCase {

    ProductInformation updateProduct(Long productId, ProductUpdateData productUpdateData);
}
