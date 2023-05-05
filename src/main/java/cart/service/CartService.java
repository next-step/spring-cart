package cart.service;

import cart.domain.CartEntity;
import cart.domain.ProductEntity;
import cart.presentation.dto.CartAddRequest;
import cart.presentation.dto.CartDetailResponse;
import cart.repository.CartRepository;
import cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Long addProductInCart(Long memberId, CartAddRequest request) {
        return this.cartRepository.save(request.toEntity(memberId));
    }

    public void removeCart(Long cartId) {
        this.cartRepository.deleteById(cartId);
    }

    public List<CartDetailResponse> cartProducts(Long memberId) {
        List<CartEntity> carts = cartRepository.findByMemberId(memberId);
        return carts.stream()
                .map(cart -> CartDetailResponse.of(cart, this.findCartItem(cart)))
                .collect(Collectors.toList());
    }

    private ProductEntity findCartItem(CartEntity cart) {
        return productRepository.findById(cart.getProductId());
    }
}
