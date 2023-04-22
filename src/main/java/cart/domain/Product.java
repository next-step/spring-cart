package cart.domain;

import cart.value.ProductRequest;

public class Product {
    private Long id;
    private int price;
    private String name;
    private String imageUrl;

    public Product(Long id, int price, String name, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Product(int price, String name, String imageUrl) {
        this(null, price, name, imageUrl);
    }

    public static Product from(ProductRequest productRequest) {
        return new Product(productRequest.getPrice(), productRequest.getName(), productRequest.getImageUrl());
    }

    public void update(ProductRequest productRequest) {
        this.price = productRequest.getPrice();
        this.name = productRequest.getName();
        this.imageUrl = productRequest.getImageUrl();
    }

    public void setId(Long id) {
        this.id = id;
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
