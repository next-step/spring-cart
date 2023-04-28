package cart.product.application.usecase;

import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductUpdateData;

public interface ProductUpdateUseCase {

    ProductInformation updateProduct(Long productId, ProductUpdateData productUpdateData);
}
