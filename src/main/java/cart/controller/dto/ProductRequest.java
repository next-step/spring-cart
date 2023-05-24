package cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "상품 이름은 필수 값 입니다.")
    private String name;

    @NotBlank(message = "상품 이미지는 필수 값 입니다.")
    private String image;

    @NotNull(message = "상품의 가격은 필수 값 입니다.")
    private int price;

}
