package cart.domain;

import lombok.Getter;

import java.util.List;
@Getter
public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }
}
