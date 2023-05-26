package cart.controller.dto.response;

import cart.domain.Cart;
import cart.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartResponse {

    private Long id;
    private Long productId;
    private String name;
    private String image;
    private int price;

    public static CartResponse of (Cart cart) {
        return new CartResponse(cart.getId(), cart.getProduct().getId(), cart.getProduct().getName(), cart.getProduct().getImage(), cart.getProduct().getPrice());
    }

}
