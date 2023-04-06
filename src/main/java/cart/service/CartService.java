package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cart.domain.Cart;
import cart.domain.Product;
import cart.repository.CartRepository;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<Cart> getList() {
        return cartRepository.findAll();
    }

    @Transactional
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart create(String authorization, long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        Cart cart = Cart.builder().product(product).build();
        return cartRepository.save(cart);
    }

    @Transactional
    public void delete(long id) {
        cartRepository.deleteById(id);
    }
}
