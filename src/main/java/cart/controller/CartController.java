package cart.controller;

import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.dto.CartDetailDto;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.CartService;
import cart.service.MemberService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CartController {

  private final CartService cartService;
  private final MemberService memberService;
  private final BasicAuthorizationExtractor basicAuthorizationExtractor = new BasicAuthorizationExtractor();

  @GetMapping("/carts")
  public ResponseEntity<List<CartDetailDto>> cartItemsForMember(HttpServletRequest request) {
    String email = basicAuthorizationExtractor.extract(request).getEmail();
    Member member = memberService.findByEmail(email);

    List<CartDetailDto> responses = cartService.cartProducts(email, member.getPassword());
    return ResponseEntity.ok(responses);
  }

  @PostMapping("/add-to-cart")
  public ResponseEntity<String> addItemToCart(HttpServletRequest request,
      @RequestBody CartCreateDto createDto) {

    String email = basicAuthorizationExtractor.extract(request).getEmail();
    Member member = memberService.findByEmail(email);
    cartService.addItem(createDto, email, member.getPassword());

    return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
  }

  @DeleteMapping("/cart/{cartId}")
  public ResponseEntity<Void> removeCart(HttpServletRequest request, @PathVariable Long cartId) {
    String email = basicAuthorizationExtractor.extract(request).getEmail();
    Member member = memberService.findByEmail(email);

    cartService.removeCart(cartId, member);

    return ResponseEntity.noContent().build();
  }

}
