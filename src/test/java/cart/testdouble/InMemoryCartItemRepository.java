package cart.testdouble;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryCartItemRepository implements CartItemRepository {

    private Map<Long, CartItem> cartItemMap = new HashMap<>();

    @Override
    public void insert(CartItem cartItem) {
        cartItemMap.put(cartItem.getId(), cartItem);
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemMap.remove(findByCartItem(cartItem).orElseThrow().getId());
    }

    @Override
    public Collection<CartItem> findAllByMemberId(Long memberId) {
        return cartItemMap.values()
                .stream()
                .filter(item -> memberId.equals(item.getMemberId()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<CartItem> findAllByProductId(Long productId) {
        return cartItemMap.values()
                .stream()
                .filter(item -> productId.equals(item.getProductId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartItem> findByCartItem(CartItem cartItem) {
        return cartItemMap.values()
                .stream()
                .filter(item -> cartItem.getMemberId().equals(item.getMemberId()) && cartItem.getProductId().equals(item.getProductId()))
                .findAny();
    }
}
