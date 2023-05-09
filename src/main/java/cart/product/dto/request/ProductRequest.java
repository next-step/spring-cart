package cart.product.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "상품명은 필수 값 입니다.")
    private String name;

    @NotBlank(message = "이미지 URL은 필수 값 입니다.")
    private String img;

    @Min(value = 0, message = "가격은 0이상만 가능합니다.")
    private Long price;
}
