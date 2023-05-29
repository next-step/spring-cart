package cart.controller;

import cart.config.Login;
import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.dto.CartDetailDto;
import cart.dto.MemberDto;
import cart.service.CartService;
import cart.service.MemberService;
import java.util.List;
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

  @GetMapping("/carts")
  public ResponseEntity<List<CartDetailDto>> cartItemsForMember(@Login Member member) {
    List<CartDetailDto> responses = cartService.cartProducts(member);

    return ResponseEntity.ok(responses);
  }

  @PostMapping("/add-to-cart")
  public ResponseEntity<String> addItemToCart(@Login Member member,
      @RequestBody CartCreateDto createDto) {
    cartService.addItem(createDto, member.getId());

    return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
  }

  @DeleteMapping("/cart/{cartId}")
  public ResponseEntity<Void> removeCart(@Login Member member, @PathVariable Long cartId) {
    cartService.removeCart(cartId, member);

    return ResponseEntity.noContent().build();
  }

}
