package cart.product.adapter.web.request;

import cart.product.application.dto.ProductUpdateData;

public class AdminProductUpdateRequest {

    private String name;
    private String image;
    private Long price;

    protected AdminProductUpdateRequest() {
    }

    public AdminProductUpdateRequest(String name, String image, Long price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public ProductUpdateData toProductUpdateData() {
        return new ProductUpdateData(getName(), getImage(), getPrice());
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
