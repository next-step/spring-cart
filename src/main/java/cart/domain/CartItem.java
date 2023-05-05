package cart.domain;

import java.util.Objects;

public class CartItem {
    private Long id;
    private Long productId;
    private Long memberId;

    public CartItem(Long id, Long productId, Long memberId) {
        this.id = id;
        this.productId = productId;
        this.memberId = memberId;
    }

    public CartItem(Long productId, Long memberId) {
        this(null, productId, memberId);
    }

    public boolean isOwnedBy(Long memberId) {
        return Objects.equals(this.memberId, memberId);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
