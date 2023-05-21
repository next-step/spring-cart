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


@RequiredArgsConstructor
@Service
public class CartService {

  private final CartRepository cartRepository;
  private final MemberService memberService;
  private final ProductService productService;

  public List<Cart> findAll() {
    List<Cart> carts = cartRepository.findAll();
    if (carts == null) {
      throw new NotFoundEntityException("Product");
    }
    return carts;
  }

  public List<CartDetailDto> cartProducts(String email, String password) {
    // 회원 인증 처리
    if (!authenticate(email, password)) {
      throw new IllegalArgumentException("인증 실패");
    }

    Member member = memberService.findByEmail(email);

    List<Cart> carts = cartRepository.findById(member.getId());
    return carts.stream()
        .map(cart -> CartDetailDto.of(cart, this.productService.findById(cart.getProductId())))
        .collect(Collectors.toList());
  }

  public void addItem(CartCreateDto createDto, String email, String password) {
    // 회원 인증 처리
    if (!authenticate(email, password)) {
      throw new IllegalArgumentException("인증 실패");
    }

    // 회원 정보 조회
    Member member = memberService.findByEmail(email);

    // 상품을 장바구니에 추가하는 비즈니스 로직 구현
    cartRepository.addProduct(member.getId(), createDto.getProductId());
  }

  private boolean authenticate(String email, String password) {
    return memberService.authenticate(email, password);
  }
}
