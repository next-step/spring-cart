package cart.controller.usecase;

import cart.dto.ProductDto;

import java.util.List;

public interface GetProductDtoUseCase {
    List<ProductDto> getProductDtos();
}
