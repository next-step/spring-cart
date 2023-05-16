package cart.application.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindProductResponse {
  private Long id;
  private String name;
  private int price;
  private String imageUrl;
}
