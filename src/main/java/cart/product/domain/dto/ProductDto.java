package cart.product.domain.dto;

import cart.product.domain.entity.Product;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String image;
    private int price;

    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }
}
