package cart.presentation.api.cartitem.response;

public class CartItemResponse {

    private final Long id;
    private final String name;
    private final Long price;

    public CartItemResponse(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
