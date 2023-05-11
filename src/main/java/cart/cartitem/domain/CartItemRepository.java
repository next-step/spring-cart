package cart.cartitem.domain;

import java.util.Optional;

public interface CartItemRepository {

    Long save(CartItem cartItem);

    Optional<CartItemWithProduct> findWithProductById(Long id);

    Optional<CartItem> findById(Long id);

    int delete(Long id);
}
