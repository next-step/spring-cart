package cart.cart.domain.dto;

import cart.cart.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private Long productId;
    private String productName;
    private Integer productPrice;
    private String productImage;
    private int count;

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
