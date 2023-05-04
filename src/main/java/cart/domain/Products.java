package cart.domain;

import java.util.List;

public class Products {

    private List<Product> value;

    public Products(List<Product> value) {
        this.value = value;
    }

    public List<Product> getValue() {
        return value;
    }

}
