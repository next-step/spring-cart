package cart.testdouble;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCartItemRepository implements CartItemRepository {

    private Map<Long, CartItem> cartItemMap = new HashMap<>();

    @Override
    public void insert(CartItem cartItem) {
        cartItemMap.put(cartItem.getId(), cartItem);
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemMap.remove(findByCartItem(cartItem).getId());
    }

    @Override
    public Collection<CartItem> findAllByMemberId(Long memberId) {
        return cartItemMap.values()
                .stream()
                .filter(item -> memberId.equals(item.getMemberId()))
                .collect(Collectors.toList());
    }

    @Override
    public CartItem findByCartItem(CartItem cartItem) {
        List<CartItem> target = cartItemMap.values()
                .stream()
                .filter(item -> cartItem.getMemberId().equals(item.getMemberId()) && cartItem.getProductId().equals(item.getProductId()))
                .collect(Collectors.toList());
        if (target.isEmpty()) {
            throw new NoSuchElementException("카트에 해당 상품이 존재하지 않습니다.");
        }
        return target.get(0);
    }
}
