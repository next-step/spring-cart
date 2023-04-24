package cart.service;

import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addCart(Long memberId,Long productId) {
        Cart cart = new Cart(memberId, productId);
        cartRepository.addCart(cart);
    }

    public List<Product> getCart(Long memberId) {
        return cartRepository.getCart(memberId);
    }
}
