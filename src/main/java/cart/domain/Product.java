package cart.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Product {

  private final Long id;
  private final String name;
  private final String image;
  private final BigDecimal price;
}
