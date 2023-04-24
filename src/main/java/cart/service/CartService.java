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

    public void addCart(Cart cart) {
        cartRepository.addCart(cart);
    }

    public List<Product> getCart(Member member) {
        return cartRepository.getCart(member.getId());
    }

    public void deleteCart(Cart cart) {
        cartRepository.deleteCart(cart);
    }
}
