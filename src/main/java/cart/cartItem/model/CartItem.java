package cart.cartItem.model;

import cart.product.model.Product;
import lombok.Data;

@Data
public class CartItem {

    private Long id;

    private Long memberId;

    private Long productId;

    private Product product;
}
