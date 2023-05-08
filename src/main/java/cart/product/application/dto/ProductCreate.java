package cart.product.application.dto;

import cart.product.domain.Product;

public class ProductCreate {

    private final String name;
    private final String image;
    private final int price;

    public ProductCreate(String name, String image, int price) {
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
