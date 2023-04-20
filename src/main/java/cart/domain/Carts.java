package cart.domain;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Carts {

    private final Map<Long, Cart> cartContainer = new HashMap<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Cart add(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(incrementKey.addAndGet(1L));
        }
        cartContainer.put(cart.getId(), cart);
        return cart;
    }

    public List<Cart> getAll() {
        return cartContainer.values()
            .stream()
            .sorted((p1, p2) -> (int) (p1.getId() - p2.getId()))
            .collect(Collectors.toList());
    }

    public void remove(Cart cart) {
        if (!cartContainer.containsKey(cart.getId())) {
            throw new NoSuchElementException();
        }
        cartContainer.remove(cart.getId());
    }

    public Optional<Cart> findById(Long id) {
        if (!cartContainer.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(cartContainer.get(id));
    }
}
