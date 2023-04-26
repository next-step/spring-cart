package cart.cartitem.persistence;

import cart.cartitem.domain.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CartItemInMemoryRepository implements CartItemRepository {

    private final Map<CartItemId, CartItem> cartItems = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1L);

    public List<CartItemEntity> findAllByUserId(CartItemUserId userId) {
        return cartItems.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getUserId().equals(userId))
                .map(entry -> new CartItemEntity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public CartItemEntity save(CartItem cartItem) {
        CartItemId cartItemId = new CartItemId(idGenerator.getAndIncrement());
        cartItems.put(cartItemId, cartItem);
        return new CartItemEntity(cartItemId, cartItem);
    }
}
