package cart.cart.domain;

public class Cart {

    private Long id;
    private Long memberId;
    private Long productId;

    private Cart(Long id, long memberId, long productId) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
    }

    public static Cart of(long memberId, long productId) {
        return Cart.builder()
                .memberId(memberId)
                .productId(productId)
                .build();
    }

    public static CartBuilder builder() {
        return new CartBuilder();
    }

    public Long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getProductId() {
        return productId;
    }

    public static class CartBuilder {
        private Long id;
        private Long memberId;
        private Long productId;

        public CartBuilder id(long id) {
            this.id = id;
            return this;
        }

        public CartBuilder memberId(long memberId) {
            this.memberId = memberId;
            return this;
        }

        public CartBuilder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public Cart build() {
            return new Cart(id, memberId, productId);
        }
    }

}
