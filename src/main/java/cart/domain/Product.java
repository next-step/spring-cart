package cart.domain;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product {
  private Long id;
  private String name;
  private int price;
  private String imageUrl;
}
