package cart.controller.response;

import cart.domain.Product;

public class CartResponse {
    public CartResponse(long id, String name, String image, long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    private final long id;
    private final String name;
    private final String image;
    private final long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getPrice() {
        return price;
    }

    public static CartResponse extract(long cartId, Product product) {
        return new CartResponse(cartId, product.getName(), product.getImage(), product.getPrice());
    }
}
