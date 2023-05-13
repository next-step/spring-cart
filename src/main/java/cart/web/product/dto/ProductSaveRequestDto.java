package cart.web.product.dto;

import cart.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductSaveRequestDto {

    private String name;
    private String imageUrl;
    private int price;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();
    }

}
