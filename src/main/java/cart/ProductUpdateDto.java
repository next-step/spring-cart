package cart;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class ProductUpdateDto {

  @NonNull
  Long id;
  @NonNull
  String name;
  @NonNull
  String image;
  @NonNull
  BigDecimal price;

  public Product toEntity(Product product) {
    return Product.builder()
        .id(getId())
        .name(getName())
        .image(getImage())
        .price(getPrice())
        .build();
  }
}
