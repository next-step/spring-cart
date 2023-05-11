package cart.product.domain.entity;

import cart.product.domain.vo.ImagePath;
import cart.product.domain.vo.ProductName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private ProductName name;
    private ImagePath image;
    private int price;
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.getName();
    }
    public String getImage() {
        return image.getPath();
    }
}
