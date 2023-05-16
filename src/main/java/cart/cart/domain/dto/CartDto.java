package cart.cart.domain.dto;

import cart.cart.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartDto {
    private final Long id;
    private final Long productId;
    private final String productName;
    private final Integer productPrice;
    private final String productImage;
    private final int count;

    public static CartDto from(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .productId(cart.getProductId())
                .productName(cart.getProductName())
                .productPrice(cart.getProductPrice())
                .productImage(cart.getProductImage())
                .count(cart.getCount())
                .build();
    }
}
