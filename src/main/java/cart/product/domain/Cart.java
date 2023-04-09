package cart.product.domain;

public class Cart {

    private Integer id;
    private String email;
    private Integer productId;

    public Cart(Integer id, String email, Integer productId) {
        this.id = id;
        this.email = email;
        this.productId = productId;
    }

    public Cart(String email, Integer productId) {
        this.email = email;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getProductId() {
        return productId;
    }
}
