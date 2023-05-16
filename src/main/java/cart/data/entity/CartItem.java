package cart.data.entity;

import java.time.LocalDateTime;

public class CartItem {
    private Long itemId;
    private Long memberId;
    private Long productId;
    private LocalDateTime createdAt;

    public CartItem(Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
        this.createdAt = LocalDateTime.now();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
