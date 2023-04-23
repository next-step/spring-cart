package cart.domain;

import cart.exception.CartException;

public class Cart {

    private Long id;
    private Long memberId;
    private Long productId;

    public Cart(Long id, Long memberId, Long productId) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
    }

    public static Cart of(Member member, Product product) {
        return new Cart(null, member.getId(), product.getId());
    }

    public void update(Cart cart) {
        if (!this.id.equals(cart.id)) {
            throw new CartException();
        }
        this.productId = cart.productId;
        this.memberId = cart.memberId;
    }

    public boolean isOwner(Member member) {
        return this.memberId.equals(member.getId());
    }

    public boolean isProduct(Product product) {
        return this.productId.equals(product.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }
}
