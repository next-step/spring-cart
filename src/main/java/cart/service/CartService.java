package cart.service;

import cart.domain.Cart;
import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.dto.CartDetailDto;
import cart.exception.NotFoundEntityException;
import cart.repository.CartRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CartService {

  private final CartRepository cartRepository;
  private final MemberService memberService;
  private final ProductService productService;

  public List<Cart> findAll() {
    List<Cart> carts = cartRepository.findAll();
    if (carts.isEmpty()) {
      throw new NotFoundEntityException("Product");
    }
    return carts;
  }

  public List<CartDetailDto> cartProducts(Member member) {
    authenticate(member);

    List<Cart> carts = cartRepository.findById(member.getId());
    return carts.stream()
        .map(cart -> CartDetailDto.of(cart, this.productService.findById(cart.getProductId())))
        .collect(Collectors.toList());
  }

  @Transactional
  public void addItem(CartCreateDto createDto, Long memberId) {
    // 상품을 장바구니에 추가하는 비즈니스 로직 구현
    cartRepository.addProduct(memberId, createDto.getProductId());
  }

  private void authenticate(Member member) {
    if (!memberService.authenticate(member)) {
      throw new IllegalArgumentException("인증 실패");
    }
  }

  public void removeCart(Long cartId, Member member) {
    authenticate(member);
    List<Cart> carts = cartRepository.findById(member.getId());
    isUserCart(member, carts);

    cartRepository.removeCart(cartId, member.getId());
  }

  private static void isUserCart(Member member, List<Cart> carts) {
    boolean isOwnedByMember = carts.stream()
        .anyMatch(cart -> cart.getMemberId().equals(member.getId()));

    if (!isOwnedByMember) {
      throw new IllegalArgumentException("본인 카트가 아닙니다.");
    }
  }
}
