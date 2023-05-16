package cart.application.dto;

import lombok.Getter;

@Getter
public class CreateProductRequest {
  private String name;
  private int price;
  private String imageUrl;
}
