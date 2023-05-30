package cart.dto;

import cart.domain.Cart;
import cart.domain.Product;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartDetailDto {

  private final Long id;
  private final String cartItemName;
  private final String cartItemImageUrl;
  private final BigDecimal cartItemPrice;

  public static CartDetailDto of(Cart cart, Product product) {
    return new CartDetailDto(
        cart.getId(),
        product.getName(),
        product.getImage(),
        product.getPrice()
    );
  }
}
