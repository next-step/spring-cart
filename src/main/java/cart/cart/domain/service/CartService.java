package cart.cart.domain.service;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.entity.Cart;
import cart.cart.domain.repository.CartRepository;
import cart.cart.domain.vo.CartId;
import cart.member.domain.entity.Member;
import cart.member.domain.repository.MemberRepository;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartDto> getCartsByMemberId(Long memberId) {
        return cartRepository.findByMemberId(memberId).stream()
                .map(CartDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addCart(Long memberId, Long productId) {
        Optional<Cart> optionalCart = cartRepository.findByProductIdAndMemberId(memberId, productId);

        if (optionalCart.isEmpty()) {
            return cartRepository.insert(Cart.makeNewCart(memberId, productId));
        }

        Cart cart = optionalCart.get();
        cart.increaseCount(1);
        cartRepository.update(cart);
        return cart.getId();
    }

    @Transactional
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cartRepository.delete(cart);
    }
}
