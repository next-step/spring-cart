package cart.controller.dto;

import cart.domain.Product;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductRequest {

    private String name;
    private String image;
    private int price;

    public Product productOf() {
        return Product.of(name, image, price);
    }
}
