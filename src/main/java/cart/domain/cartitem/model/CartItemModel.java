package cart.domain.cartitem.model;

public class CartItemModel {

    private Long id;
    private String memberEmail;
    private Long productId;

    public CartItemModel(String memberEmail, Long productId) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.productId = productId;
    }

    public void addId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public Long getProductId() {
        return productId;
    }

    public boolean ownsBy(String email) {
        return this.memberEmail.equals(email);
    }
}
