package cart.domain.cartitem;

import cart.domain.cartitem.model.CartItemModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartItemRepository {

    private static final List<CartItemModel> CART_ITEMS = new ArrayList<>();
    private static Long id = 0L;

    public Long createNewCart(String memberEmail, Long productId) {
        CartItemModel cartItemModel = new CartItemModel(memberEmail, productId);
        cartItemModel.addId(++id);
        CART_ITEMS.add(cartItemModel);
        return id;
    }

    public List<CartItemModel> findByMemberEmail(String memberEmail) {
        return CART_ITEMS.stream()
            .filter(cartItemModel -> cartItemModel.getMemberEmail().equals(memberEmail))
            .collect(Collectors.toList());
    }

    public void removeProducts(Long cartId) {
        CART_ITEMS.stream()
            .filter(cartItemModel -> cartItemModel.getId().equals(cartId))
            .findAny()
            .ifPresent(CART_ITEMS::remove);
    }

    public Optional<CartItemModel> findById(Long cartItemId) {
        return CART_ITEMS.stream()
            .filter(cartItemModel -> cartItemModel.getId().equals(cartItemId))
            .findAny();
    }
}
