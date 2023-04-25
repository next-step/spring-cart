package cart.cartitem.application;

import cart.cartitem.application.dto.CartItemInformation;
import cart.cartitem.application.usecase.GetCartItemInformationUseCase;
import cart.cartitem.domain.CartItemRepository;
import cart.cartitem.domain.CartItemUserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class GetCartItemInformationService implements GetCartItemInformationUseCase {

    private final CartItemRepository cartItemRepository;

    public GetCartItemInformationService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public List<CartItemInformation> getCartItemInformations(Long userId) {
        CartItemUserId cartItemUserId = new CartItemUserId(userId);
        return cartItemRepository.findAllByUserId(cartItemUserId)
                .stream()
                .map(CartItemInformation::fromCartItemEntity)
                .collect(Collectors.toList());
    }
}
