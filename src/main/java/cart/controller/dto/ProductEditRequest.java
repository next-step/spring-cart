package cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ProductEditRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String image;

    @NotBlank
    private int price;
}
