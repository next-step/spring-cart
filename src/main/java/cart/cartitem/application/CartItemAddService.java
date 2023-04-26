package cart.cartitem.application;

import cart.cartitem.application.dto.CartItemAddData;
import cart.cartitem.application.dto.CartItemInformation;
import cart.cartitem.application.usecase.CartItemAddUseCase;
import cart.cartitem.domain.CartItem;
import cart.cartitem.domain.CartItemEntity;
import cart.cartitem.domain.CartItemRepository;
import cart.product.domain.ProductEntity;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemAddService implements CartItemAddUseCase {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemAddService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItemInformation addCartItem(CartItemAddData cartItemAddData) {
        ProductEntity productEntity = productRepository.findById(cartItemAddData.getProductId());
        CartItem cartItem = new CartItem(
                cartItemAddData.getUserId(),
                productEntity.getIdValue(),
                productEntity.getNameValue(),
                productEntity.getImageValue(),
                productEntity.getPriceLongValue()
        );
        CartItemEntity cartItemEntity = cartItemRepository.save(cartItem);
        return CartItemInformation.fromCartItemEntity(cartItemEntity);
    }
}
