package cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductEditRequest {

    private String name;
    private String image;
    private int price;
}
