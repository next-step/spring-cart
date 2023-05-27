package cart.service;

import cart.controller.dto.request.CartAddRequest;
import cart.controller.dto.response.CartResponse;
import cart.controller.dto.response.CartsResponse;
import cart.domain.Cart;
import cart.domain.Product;
import cart.domain.User;
import cart.exception.ErrorCode;
import cart.exception.JwpCartApplicationException;
import cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static cart.exception.ErrorCode.CART_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartRepository cartRepository;


    public CartsResponse findAll(User user) {
        List<Cart> carts = cartRepository.findAllByUserId(user.getId());
        return CartsResponse.of(carts.stream().map(CartResponse::of).collect(Collectors.toList()));
    }

    public CartResponse add(User user, CartAddRequest cartAddRequest) {
        Product product = productService.findById((long) cartAddRequest.getProductId());
        Cart saveCart = cartRepository.add(user, product);
        return CartResponse.of(saveCart);
    }

    public void delete(User user, Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new JwpCartApplicationException(CART_NOT_FOUND));
        if (!cart.checkAuthority(user)) {
            throw new JwpCartApplicationException(ErrorCode.UNAUTHORIZED_CART);
        }
        cartRepository.delete(cartId);
    }


}
