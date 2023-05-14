package cart.web.cart.dto;

import cart.domain.product.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class CartResponseDto {

    private Long cartId;

    private Long productId;
    private String name;
    private String imageUrl;
    private int price;

    public CartResponseDto(Long cartId, Product productEntity) {
        this.cartId = cartId;

        this.productId = productEntity.getId();
        this.name = productEntity.getName();
        this.imageUrl = productEntity.getImageUrl();
        this.price = productEntity.getPrice();
    }
}
