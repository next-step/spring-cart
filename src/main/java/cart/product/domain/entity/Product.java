package cart.product.domain.entity;

import cart.product.domain.vo.ImagePath;
import cart.product.domain.vo.Price;
import cart.product.domain.vo.ProductId;
import cart.product.domain.vo.ProductName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Product {
    private ProductId id;
    private ProductName name;
    private ImagePath image;
    private Price price;
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = new ProductId(id);
    }
    public Long getId() { return id.getId(); }

    public String getName() {
        return name.getName();
    }
    public String getImage() {
        return image.getPath();
    }
    public int getPrice() {
        return this.price.getPrice();
    }
}
