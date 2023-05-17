package cart.dto;

import cart.domain.Product;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductReadDto {

  private final Long id;
  private final String name;
  private final String image;
  private final BigDecimal price;

  public static ProductReadDto toDto(Product product) {
    return ProductReadDto.builder()
        .id(product.getId())
        .name(product.getName())
        .image(product.getImage())
        .price(product.getPrice())
        .build();
  }
}
