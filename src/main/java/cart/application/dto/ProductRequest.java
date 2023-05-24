package cart.application.dto;

import lombok.Getter;

@Getter
public class ProductRequest {
  private String name;
  private int price;
  private String imageUrl;
}
