package cart.product.controller.dto;

import cart.product.domain.Product;

public class ProductRequest {

    private String name;
    private String image;
    private Long price;

    public Product toProduct() {
        return Product.of(name, image, price);
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Long getPrice() {
        return price;
    }

}
