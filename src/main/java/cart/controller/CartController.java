package cart.controller;

import cart.domain.Cart;
import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.dto.CartDetailDto;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.CartService;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartController {

  private final CartService cartService;
  private BasicAuthorizationExtractor basicAuthorizationExtractor = new BasicAuthorizationExtractor();

  @GetMapping("/carts")
  public ResponseEntity<List<CartDetailDto>> cartItemsForMember(
      Member member,
      @RequestHeader(value = "Authorization", required = false) String authorization) {
    if (authorization == null || !authorization.startsWith("Basic ")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String credentials = authorization.substring("Basic ".length());
    String decodedCredentials = new String(Base64.getDecoder().decode(credentials));

    String[] splitCredentials = decodedCredentials.split(":");
    String email = splitCredentials[0];
    String password = splitCredentials[1];

    // TODO: 인증 및 인가 로직 수행

    List<CartDetailDto> responses = cartService.cartProducts(email,password);
    return ResponseEntity.ok(responses);
  }

  @PostMapping("/add-to-cart")
  public ResponseEntity<String> addItemToCart(@RequestBody CartCreateDto createDto,
      @RequestHeader("Authorization") String authorization) {
    String credentials = authorization.substring("Basic ".length());
    String decodedCredentials = new String(Base64.getDecoder().decode(credentials));

    String[] splitCredentials = decodedCredentials.split(":");
    String email = splitCredentials[0];
    String password = splitCredentials[1];

    cartService.add(createDto, email, password);

    return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
  }

}
