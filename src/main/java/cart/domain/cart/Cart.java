package cart.domain.cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Cart {

    private Long id;
    private Long userId;
    private Long productId;

    @Builder
    public Cart(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }
}
