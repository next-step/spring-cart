package cart.controller.request;

import cart.dto.ProductRegistrationData;

public class AdminProductRegistrationRequest {

    private String name;
    private String image;
    private Long price;

    protected AdminProductRegistrationRequest() {
    }

    public AdminProductRegistrationRequest(String name, String image, Long price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public ProductRegistrationData toProductRegistrationData() {
        return new ProductRegistrationData(getName(), getImage(), getPrice());
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
