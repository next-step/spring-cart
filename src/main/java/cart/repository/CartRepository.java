package cart.repository;

import cart.domain.Cart;
import cart.domain.Product;
import cart.domain.User;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findById(Long id);

    List<Cart> findAllByUserId(Long userId);

    Cart add(User user, Product product);

    void delete(Long cartId);

    void deleteAll();

    int count();

}
