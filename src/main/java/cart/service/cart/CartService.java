package cart.service.cart;

import cart.domain.cart.Cart;
import cart.domain.product.Product;
import cart.domain.user.User;
import cart.infrastructure.dao.CartDao;
import cart.infrastructure.dao.ProductDao;
import cart.infrastructure.security.AccessDeniedException;
import cart.service.cart.dto.CartAddRequestDto;
import cart.service.cart.dto.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartService {

    private final CartDao cartDao;
    private final ProductDao productDao;

    public List<CartResponseDto> findAll(User user) {
        List<Cart> userCarts = cartDao.findAllByUserId(user.getId());

        return userCarts.stream()
                .map(userCart -> new CartResponseDto(userCart.getId(), userCart.getProduct()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long add(User user, CartAddRequestDto requestDto) {
        Optional<Product> product = productDao.findById(requestDto.getProductId());
        if (product.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        Cart cart = Cart.builder()
                .user(user)
                .product(product.get())
                .build();
        return cartDao.insert(cart).getId();
    }

    @Transactional
    public Long remove(User user, Long cartId) {
        Cart cart = cartDao.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니 아이템입니다."));

        if (checkAuthority(user, cart)) {
            throw new AccessDeniedException("권한이 없는 장바구니 아이템입니다.");
        }

        return cartDao.delete(cartId);
    }

    private boolean checkAuthority(User user, Cart cart) {
        return !cart.getUser().equals(user);
    }

}
