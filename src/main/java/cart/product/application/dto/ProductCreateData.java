package cart.product.application.dto;

import cart.product.domain.Product;

public class ProductCreateData {

    private final String name;
    private final String image;
    private final Integer price;

    public ProductCreateData(String name, String image, Integer price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public Product toProduct() {
        return new Product(null, name, image, price);
    }
}
