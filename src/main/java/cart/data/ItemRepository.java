package cart.data;

import cart.data.entity.CartItem;
import cart.presentation.dto.ViewCartItem;

import java.util.List;

public interface ItemRepository {

    void addToCart(CartItem item);

    List<ViewCartItem> getCartItems(Long memberId);

    int removeFromCart(Long id);
}
