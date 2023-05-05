package cart.value;

import cart.domain.Product;

public class ProductResponse {
    private final Long id;
    private final int price;
    private final String name;
    private final String imageUrl;

    private ProductResponse(Long id, int price, String name, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getPrice(), product.getName(), product.getImageUrl());
    }

    public Long getId() {
        return id;
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
