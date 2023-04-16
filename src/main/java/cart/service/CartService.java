package cart.service;

import cart.controller.response.CartResponse;
import cart.controller.response.MemberResponse;
import cart.domain.Cart;
import cart.domain.Product;
import cart.repository.CartRepository;
import cart.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void save(MemberResponse memberResponse, Long productId) {
        Product product = productRepository.findById(productId);
        cartRepository.save(new Cart(memberResponse.getId(), product.getId()));
    }

    public List<CartResponse> findAll(MemberResponse memberResponse) {
        List<Cart> carts = cartRepository.findAll(memberResponse.getId());
        return carts.stream().map(cart -> {
            Product product = productRepository.findById(cart.getProductId());
            return CartResponse.extract(cart.getId(), product);
        }).collect(Collectors.toList());
    }

    public void deleteById(MemberResponse memberResponse, Long id) {
        cartRepository.deleteById(memberResponse.getId(), id);
    }
}
