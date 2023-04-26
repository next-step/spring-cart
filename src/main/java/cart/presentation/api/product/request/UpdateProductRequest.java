package cart.presentation.api.product.request;

public class UpdateProductRequest {

    private final String name;
    private final String imageUrl;
    private final Long price;

    public UpdateProductRequest(String name, String imageUrl, Long price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPrice() {
        return price;
    }
}
