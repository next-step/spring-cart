package cart.product.web.dto;

import lombok.*;

public class DeleteProduct {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private Long id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    public static class Response {

    }
}
