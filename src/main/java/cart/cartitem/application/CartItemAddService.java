package cart.cartitem.application;

import cart.cartitem.application.dto.CartItemAddData;
import cart.cartitem.application.dto.CartItemInformation;
import cart.cartitem.application.usecase.CartItemAddUseCase;
import cart.cartitem.domain.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CartItemAddService implements CartItemAddUseCase {

    private final CartItemRepository cartItemRepository;

    public CartItemAddService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemInformation addCartItem(CartItemAddData cartItemAddData) {
        return null;
    }
}
