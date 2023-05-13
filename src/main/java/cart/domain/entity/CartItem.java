package cart.domain.entity;

public class CartItem {
    private Long id;
    private Long memberId;
    private Long productId;

    public CartItem() {}

    public CartItem(Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }

    public CartItem(Long id, Long memberId, Long productId) {
        this(memberId, productId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }
}
