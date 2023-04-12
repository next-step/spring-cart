package cart.cartItem;

import cart.cartItem.model.CartItem;
import cart.cartItem.model.CartItemEntity;
import cart.cartItem.model.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Transactional(readOnly = true)
    public List<CartItem> findAllByMemberId(Long memberId) {

        return cartItemRepository.findAllByMemberId(memberId).stream()
                .map(CartItemMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public CartItem cartInsert(Long memberId, Long productId) {

        var cartItemEntity = new CartItemEntity(memberId, productId);

        return CartItemMapper.INSTANCE.toDto(cartItemRepository.save(cartItemEntity));
    }

    @Transactional
    public void cartDelete(Long id) {

        cartItemRepository.deleteById(id);
    }
}
