package cart.model;

import java.util.List;

public class Carts {

    private List<Cart> carts;

    public Carts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }
}
