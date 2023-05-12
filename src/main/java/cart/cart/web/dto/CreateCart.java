package cart.cart.web.dto;

import cart.cart.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CreateCart {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Request {
        private Long memberId;
        private Long productId;

        public static Request of(Long memberId, Long productId) {
            return Request.builder()
                    .memberId(memberId)
                    .productId(productId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long cartId;

        public static Response of(Long cartId) {
            return Response.builder()
                    .cartId(cartId)
                    .build();
        }
    }
}
