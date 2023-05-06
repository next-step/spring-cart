package cart.product.web.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateProduct {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        @NotEmpty
        private String productName;
        @NotEmpty
        private String image;
        @NotNull
        @Min(0)
        private int price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    public static class Response {

    }
}
