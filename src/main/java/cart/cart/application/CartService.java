package cart.cart.application;

import cart.auth.authentication.Auth;
import cart.cart.controller.dto.AddProductRequest;
import cart.cart.controller.dto.CartProductResponse;
import cart.cart.domain.Cart;
import cart.cart.domain.CartRepository;
import cart.cart.domain.Carts;
import cart.product.domain.ProductRepository;
import cart.product.domain.Products;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartProductResponse> findCartProduct(long id) {
        Carts carts = cartRepository.findByMemberId(id);
        List<Long> productIds = carts.getProductIds();
        Products products = productRepository.findByIds(productIds);

        return products.getValue()
                .stream()
                .map(CartProductResponse::of)
                .collect(Collectors.toList());
    }

    public void addProduct(Auth auth, AddProductRequest request) {
        Cart cart = Cart.of(auth.getId(), request.getProductId());
        cartRepository.addProduct(cart);
    }

    public void deleteCartProduct(Auth auth, Long productId) {
        Cart cart = Cart.of(auth.getId(), productId);
        cartRepository.deleteCart(cart);
    }
}
