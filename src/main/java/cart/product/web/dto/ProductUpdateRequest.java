package cart.product.web.dto;

import cart.product.application.dto.ProductUpdateData;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductUpdateRequest {

    private Long id;

    @NotBlank(message = "이름은 필수 값입니다.")
    private final String name;

    @NotBlank(message = "이미지는 필수 값입니다.")
    private final String image;

    @NotNull(message = "가격은 필수 값입니다.")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private final Integer price;

    public ProductUpdateRequest(Long id, String name, String image, Integer price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void injectId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public ProductUpdateData toProductUpdateData() {
        return new ProductUpdateData(id, name, image, price);
    }
}
