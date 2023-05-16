package cart.domain.repository;

import cart.domain.entity.CartItem;

import java.util.Collection;

public interface CartItemRepository {
    void insert(CartItem cartItem);
    void delete(CartItem cartItem);
    Collection<CartItem> findAllByMemberId(Long memberId);
    CartItem findByCartItem(CartItem cartItem);
}
