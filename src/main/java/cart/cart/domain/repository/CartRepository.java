package cart.cart.domain.repository;

import cart.cart.domain.entity.Cart;

import java.util.List;

public interface CartRepository {
    public List<Cart> findByMemberId(Long memberId);
}
