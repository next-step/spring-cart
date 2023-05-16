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

  private Long id;
  private String name;
  private String image;
  private BigDecimal price;

  public static ProductReadDto toDto(Product product) {
    return ProductReadDto.builder()
        .id(product.getId())
        .name(product.getName())
        .image(product.getImage())
        .price(product.getPrice())
        .build();
  }
}
