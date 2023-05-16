package cart.product.domain.dto;

import cart.product.domain.entity.Product;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ProductDto {
    private final Long id;
    private final String name;
    private final String image;
    private final int price;

    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }
}
