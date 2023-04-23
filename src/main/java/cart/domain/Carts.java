package cart.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Carts {

    private final List<Cart> cartContainer = new ArrayList<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Cart add(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(incrementKey.addAndGet(1L));
            cartContainer.add(cart);
            return cart;
        }
        final Cart saved = findById(cart.getId()).orElseThrow();
        saved.update(cart);
        return saved;
    }

    public void remove(Cart cart) {
        cartContainer.remove(cart);
    }

    public Optional<Cart> findById(Long id) {
        return cartContainer.stream()
            .filter(cart -> cart.getId().equals(id))
            .findAny();
    }

    public boolean existsByMemberAndProduct(Member member, Product product) {
        return cartContainer.stream()
            .anyMatch(cart -> cart.isOwner(member) && cart.isProduct(product));
    }

    public List<Cart> findAllByMember(Member member) {
        return cartContainer.stream()
            .filter(cart -> cart.isOwner(member))
            .collect(Collectors.toList());
    }
}
