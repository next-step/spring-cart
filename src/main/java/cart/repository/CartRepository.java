package cart.repository;

import cart.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findById(Long id);

    List<Cart> findAllByUserId(Long userId);

    Cart add(Cart cart);

    void delete(Long cartId);

}
