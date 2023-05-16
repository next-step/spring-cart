package cart.dto;

import cart.domain.Product;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class ProductCreateDto {

  @NonNull
  private String name;
  @NonNull
  private String image;
  @NonNull
  private BigDecimal price;

  public Product toEntity() {
    return Product.builder()
        .price(getPrice())
        .image(getImage())
        .name(getName())
        .build();
  }
}
