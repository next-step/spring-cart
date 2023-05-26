package cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cart {

    private Long id;
    private User user;
    private Product product;

    public Cart(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public boolean checkAuthority(User user) {
        return this.getUser().getId().equals(user.getId());
    }
}
