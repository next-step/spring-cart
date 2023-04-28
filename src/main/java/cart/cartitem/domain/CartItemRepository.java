package cart.cartitem.domain;

import java.util.List;

public interface CartItemRepository {
    List<CartItemEntity> findAllByUserId(CartItemUserId userId);

    CartItemEntity save(CartItem cartItem);
}
