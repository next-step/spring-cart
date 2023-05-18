package cart.domain;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Product {

  private Long id;
  private String name;
  private String image;
  private BigDecimal price;
}
