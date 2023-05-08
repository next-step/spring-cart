package cart.product.web.dto;

import cart.product.application.dto.ProductUpdate;

public class ProductUpdateRequest {

    private final Long id;
    private final String name;
    private final String image;
    private final int price;

    public ProductUpdateRequest(Long id, String name, String image, int price) {
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

    public ProductUpdate toProductUpdate() {
        return new ProductUpdate(id, name, image, price);
    }
}
