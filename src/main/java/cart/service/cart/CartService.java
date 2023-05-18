package cart.service.cart;

import cart.domain.cart.Cart;
import cart.domain.product.Product;
import cart.domain.user.User;
import cart.infrastructure.dao.CartDao;
import cart.infrastructure.dao.ProductDao;
import cart.infrastructure.security.AccessDeniedException;
import cart.service.cart.exception.ProductDoesNotExistException;
import cart.web.cart.dto.CartAddRequestDto;
import cart.web.cart.dto.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartService {

    private final CartDao cartDao;
    private final ProductDao productDao;

    public List<CartResponseDto> findAll(User user) {
        List<Cart> userCarts = cartDao.findAllByUserId(user.getId());
        List<Product> userProducts = userCarts.stream()
                .map(userCart -> productDao.findById(userCart.getProductId())
                        .orElseThrow(ProductDoesNotExistException::new))
                .collect(Collectors.toList());

        return IntStream.range(0, userCarts.size())
                .mapToObj(index -> new CartResponseDto(userCarts.get(index).getId(), userProducts.get(index)))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long add(User user, CartAddRequestDto requestDto) {
        Optional<Product> product = productDao.findById(requestDto.getProductId());
        if (product.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        Cart cart = Cart.builder()
                .userId(user.getId())
                .productId(requestDto.getProductId())
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
        return !cart.getUserId().equals(user.getId());
    }

}
