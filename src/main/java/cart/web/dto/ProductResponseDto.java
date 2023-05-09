package cart.web.dto;

import cart.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private Long id;
    private String name;
    private String imageUrl;
    private int price;

    public ProductResponseDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
        this.price = entity.getPrice();
    }
}
