package cart.product.web.dto;

import cart.product.application.dto.ProductCreate;

public class ProductCreateRequest {

    private final String name;
    private final String image;
    private final int price;

    public ProductCreateRequest(String name, String image, int price) {
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

    public ProductCreate toProductCreate() {
        return new ProductCreate(name, image, price);
    }
}
