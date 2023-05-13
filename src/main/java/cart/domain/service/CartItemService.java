package cart.domain.service;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;
import cart.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public Collection<CartItem> getAllByMember(Long memberId) {
        return cartItemRepository.findAllByMemberId(memberId);
    }

    public void add(CartItem cartItem) {
        cartItemRepository.insert(cartItem);
    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
