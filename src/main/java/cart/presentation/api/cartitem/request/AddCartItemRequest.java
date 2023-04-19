package cart.presentation.api.cartitem.request;

public class AddCartItemRequest {

    private Long productId;

    public AddCartItemRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
