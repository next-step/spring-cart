package cart.api.dto;

import cart.domain.entity.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductRequest {
    @NotBlank
    private String name;
    @Min(0)
    private int price;
    @NotBlank
    private String imageUrl;

    public ProductRequest() {
    }

    public ProductRequest(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product toModel() {
        return new Product(
                null,
            this.name,
            this.price,
            this.imageUrl
        );
    }
}
