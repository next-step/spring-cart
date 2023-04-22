package cart.domain.cartitem;

import cart.domain.cartitem.model.CartItemModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CartItemRepository {

    private static final List<CartItemModel> CART_ITEMS = new ArrayList<>();
    private static final AtomicLong id = new AtomicLong(0);

    public Long createNewCart(String memberEmail, Long productId) {
        long carItemId = id.incrementAndGet();
        CartItemModel cartItemModel = new CartItemModel(memberEmail, productId);
        cartItemModel.addId(carItemId);
        CART_ITEMS.add(cartItemModel);
        return carItemId;
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
