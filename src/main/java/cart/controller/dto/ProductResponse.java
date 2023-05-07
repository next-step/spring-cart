package cart.controller.dto;

import cart.domain.Product;
import lombok.Getter;
@Getter
public class ProductResponse {

    private final int id;
    private final String name;
    private final String image;
    private final int price;

    public static ProductResponse of(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getImage(), product.getPrice());
    }

    public ProductResponse(int id, String name, String image, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }
}
