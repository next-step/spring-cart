package cart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class ProductCreateDto {

  @NonNull
  String name;
  @NonNull
  String image;
  @NonNull
  BigDecimal price;

  public Product toEntity() {
    return Product.builder()
        .price(getPrice())
        .image(getImage())
        .name(getName())
        .build();
  }
}
