package cart.cart.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Carts {

    private List<Cart> value;

    public Carts(List<Cart> carts) {
        this.value = carts;
    }

    public List<Cart> getValue() {
        return value;
    }

    public List<Long> getProductIds() {
        return value.stream()
                .map(Cart::getProductId)
                .collect(Collectors.toList());
    }

}
