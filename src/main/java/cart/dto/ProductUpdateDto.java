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
public class ProductUpdateDto {

  @NonNull
  private final Long id;
  @NonNull
  private final String name;
  @NonNull
  private final String image;
  @NonNull
  private final BigDecimal price;

  public Product toEntity() {
    return Product.builder()
        .id(getId())
        .name(getName())
        .image(getImage())
        .price(getPrice())
        .build();
  }
}
