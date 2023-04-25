package cart.product.application.usecase;

import cart.product.application.dto.ProductInformation;

import java.util.List;

public interface GetProductInformationUseCase {
    List<ProductInformation> getProductInformations();
}
