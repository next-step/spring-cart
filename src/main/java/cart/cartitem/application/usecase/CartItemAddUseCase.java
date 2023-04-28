package cart.cartitem.application.usecase;

import cart.cartitem.application.dto.CartItemAddData;
import cart.cartitem.application.dto.CartItemInformation;

public interface CartItemAddUseCase {
    CartItemInformation addCartItem(CartItemAddData cartItemAddData);
}
