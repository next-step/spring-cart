package cart.cart.domain.repository;

import cart.cart.domain.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findById(Long cartId);
    Optional<Cart> findByProductIdAndMemberId(Long memberId, Long productId);
    List<Cart> findByMemberId(Long memberId);
    Long insert(Cart cart);
    int update(Cart cart);
    int delete(Cart cart);
}
