package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<Cart> getList() {
        return cartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Cart> getList(Member member) {
        return cartRepository.findByMember(member);
    }

    @Transactional
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart create(Member member, Product product) {
        Cart cart = Cart.builder().product(product).member(member).build();
        return cartRepository.save(cart);
    }

    @Transactional
    public void delete(long id) {
        cartRepository.deleteById(id);
    }
}
