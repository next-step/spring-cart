package cart.value;

public class ProductRequest {
    private final int price;
    private final String name;
    private final String imageUrl;

    public ProductRequest(int price, String name, String imageUrl) {
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
