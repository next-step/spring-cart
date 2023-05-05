package cart.service;

import cart.domain.CartItem;
import cart.domain.CartItems;
import cart.domain.Product;
import cart.domain.Products;
import cart.exception.AuthorizationException;
import cart.value.ProductsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItems cartItems;
    private final Products products;

    public CartItemService(CartItems cartItems, Products products) {
        this.cartItems = cartItems;
        this.products = products;
    }

    public ProductsResponse findAllCartItems(Long memberId) {
        List<CartItem> findCartItems = cartItems.findAllByMemberId(memberId);
        List<Product> findProducts = findCartItems.stream()
                .map(it -> products.findById(it.getProductId()))
                .collect(Collectors.toList());
        return ProductsResponse.from(findProducts);
    }

    public void deleteCartItem(Long cartItemId, Long memberId) {
        CartItem cartItem = cartItems.findById(cartItemId);
        if (!cartItem.isOwnedBy(memberId)) {
            throw new AuthorizationException("해당 아이템에 권한이 없습니다.");
        }
        cartItems.remove(cartItem);
    }
}
