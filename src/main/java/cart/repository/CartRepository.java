package cart.repository;

import cart.domain.CartEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class CartRepository {
    private final Map<Long, CartEntity> carts = new HashMap<>();
    private final AtomicLong incrementId = new AtomicLong(1);

    public Long save(CartEntity cart) {
        Long id = incrementId.getAndIncrement();
        cart.setId(id);
        this.carts.put(id, cart);
        return id;
    }

    public List<CartEntity> findByMemberId(Long memberId) {
        return this.carts.values().stream()
                .filter(cart -> cart.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    public boolean deleteById(Long id) {
        return this.carts.remove(id) != null;
    }
}
