package cart.domain.repository;

import cart.domain.entity.CartItem;

import java.util.Collection;
import java.util.Optional;

public interface CartItemRepository {
    void insert(CartItem cartItem);
    void delete(CartItem cartItem);
    Collection<CartItem> findAllByMemberId(Long memberId);
    Optional<CartItem> findByCartItem(CartItem cartItem);
}
