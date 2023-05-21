package cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Cart {

  private final Long id;
  private final Long productId;
  private final Long memberId;
  private final int count;

}
