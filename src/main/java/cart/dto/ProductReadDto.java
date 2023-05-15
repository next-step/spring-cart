package cart.dto;

import cart.domain.Product;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductReadDto {
  Long id;
  String name;
  String image;
  BigDecimal price;

  public static ProductReadDto toDto(Product product) {
    return ProductReadDto.builder()
        .id(product.getId())
        .name(product.getName())
        .image(product.getImage())
        .price(product.getPrice())
        .build();
  }
}
