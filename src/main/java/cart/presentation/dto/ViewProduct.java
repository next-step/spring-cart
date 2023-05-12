package cart.presentation.dto;

import cart.data.entity.CartProduct;

public class ViewProduct {

    private long id;
    private String name;
    private int price;
    private String imageUrl;

    public ViewProduct(CartProduct product) {
        this.id = product.getProductId();
        this.name = product.getProductName();
        this.price = product.getProductPrice();
        this.imageUrl = product.getProductImageUrl();
    }

    public long getId() {
        return id;
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
