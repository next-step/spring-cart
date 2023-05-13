package cart.web.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductUpdateRequestDto {

    private String name;
    private String imageUrl;
    private int price;

}
