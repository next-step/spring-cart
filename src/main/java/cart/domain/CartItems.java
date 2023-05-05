package cart.domain;

import cart.exception.NoSuchCartItemException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CartItems {
    private Long tmpId = 1L;
    private final List<CartItem> values = new ArrayList<>();

    public void save(CartItem cartItem) {
        cartItem.setId(tmpId++);
        values.add(cartItem);
    }

    public List<CartItem> findAllByMemberId(Long memberId) {
        return values.stream()
                .filter(it -> Objects.equals(it.getMemberId(), memberId))
                .collect(Collectors.toList());
    }

    public CartItem findById(Long cartItemId) {
        return values.stream()
                .filter(it -> Objects.equals(it.getId(), cartItemId))
                .findAny()
                .orElseThrow(NoSuchCartItemException::new);
    }

    public void remove(CartItem cartItem) {
        values.remove(cartItem);
    }
}
