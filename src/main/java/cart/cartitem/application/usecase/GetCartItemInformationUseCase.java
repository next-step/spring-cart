package cart.cartitem.application.usecase;

import cart.cartitem.application.dto.CartItemInformation;

import java.util.List;

public interface GetCartItemInformationUseCase {
    List<CartItemInformation> getCartItemInformations(Long userId);
}
