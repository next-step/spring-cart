package cart.business.domain;

import cart.presentation.dto.RequestProduct;

public class Product {

    private long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(RequestProduct product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
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
}
