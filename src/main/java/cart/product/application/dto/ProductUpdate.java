package cart.product.application.dto;

import cart.product.domain.Product;

public class ProductUpdate {

    private final Long id;
    private final String name;
    private final String image;
    private final int price;

    public ProductUpdate(Long id, String name, String image, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
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
        return new Product(id, name, image, price);
    }
}
