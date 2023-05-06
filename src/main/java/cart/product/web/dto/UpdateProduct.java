package cart.product.web.dto;

import cart.product.domain.dto.ProductDto;
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
        private String name;
        @NotEmpty
        private String image;
        @NotNull
        @Min(0)
        private int price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String image;
        private int price;

        public static UpdateProduct.Response from(ProductDto productDto) {
            return UpdateProduct.Response.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .image(productDto.getImage())
                    .price(productDto.getPrice())
                    .build();
        }

    }
}
