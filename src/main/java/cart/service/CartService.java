package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Cart;
import cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    @Transactional
    public List<Cart> getList() {
        return cartRepository.findAll();
    }

    @Transactional
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void delete(long id) {
        cartRepository.deleteById(id);
    }
}
