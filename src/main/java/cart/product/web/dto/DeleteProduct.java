package cart.product.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

public class DeleteProduct {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private Long id;
    }

    @Getter
    @Builder
    public static class Response {

    }
}
