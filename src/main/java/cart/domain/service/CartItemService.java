package cart.domain.service;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
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
