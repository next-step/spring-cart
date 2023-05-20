package cart.domain.cart;

import cart.domain.product.Product;
import cart.domain.user.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "id")
@Getter
public class Cart {

    private Long id;
    private User user;
    private Product product;

    @Builder
    public Cart(Long id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }
}
