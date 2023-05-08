package cart.product.application.dto;

import cart.product.domain.Product;

public class ProductUpdateData {

    private final Long id;
    private final String name;
    private final String image;
    private final Integer price;

    public ProductUpdateData(Long id, String name, String image, Integer price) {
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
