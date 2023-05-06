package cart.product.web.dto;

import cart.product.domain.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {
    private Long id;
    private String name;
    private String image;
    private int price;

    public static ProductInfo fromEntity(Product product) {
        return ProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }
}
