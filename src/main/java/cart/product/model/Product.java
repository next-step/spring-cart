package cart.product.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Product {

    private int id;

    @NotEmpty(message = "상품 이름 입력 필요")
    private String name;

    @NotEmpty(message = "이미지 URL 입력 필요")
    private String image;

    @NotNull(message = "금액 입력 필요")
    @Min(value = 0, message = "최소 금액 0원 이상")
    private int price;
}
