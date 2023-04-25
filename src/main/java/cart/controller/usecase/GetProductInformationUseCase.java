package cart.controller.usecase;

import cart.dto.ProductInformation;

import java.util.List;

public interface GetProductInformationUseCase {
    List<ProductInformation> getProductInformations();
}
