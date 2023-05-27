package cart.controller;

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
  public ResponseEntity<List<CartDetailDto>> cartItemsForMember(MemberDto memberDto) {
    Member member = memberService.findByEmail(memberDto.getEmail());
    List<CartDetailDto> responses = cartService.cartProducts(member);

    return ResponseEntity.ok(responses);
  }

  @PostMapping("/add-to-cart")
  public ResponseEntity<String> addItemToCart(MemberDto memberDto,
      @RequestBody CartCreateDto createDto) {
    Member member = memberService.findByEmail(memberDto.getEmail());
    cartService.addItem(createDto, member.getId());

    return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
  }

  @DeleteMapping("/cart/{cartId}")
  public ResponseEntity<Void> removeCart(MemberDto memberDto, @PathVariable Long cartId) {
    Member member = memberService.findByEmail(memberDto.getEmail());
    cartService.removeCart(cartId, member);

    return ResponseEntity.noContent().build();
  }

}
