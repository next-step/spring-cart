package cart.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import cart.domain.Cart;
import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.repository.CartRepository;
import cart.service.CartService;
import cart.service.MemberService;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import java.util.List;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("장바구니 컨트롤러 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

  private static final Header MEMBER1_AUTHORIZATION_VALUE = new Header(HttpHeaders.AUTHORIZATION,
      "Basic YUBhLmNvbTpwYXNzd29yZDE=");

  @LocalServerPort
  int port;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private MemberService memberService;

  Member member = null;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;

    List<Member> members = memberService.findAll();
    member = members.get(0);

    cartService.addItem(new CartCreateDto(1L, 1), member.getId());
  }

  @AfterEach
  void afterEach() {
    List<Cart> cartList = cartRepository.findById(member.getId());
    for (Cart cart : cartList) {
      Long cartId = cart.getId();
      cartRepository.removeCart(cartId, member.getId());
    }
  }


  @DisplayName("로그인한 유저의 장바구니 리스트를 출력한다.")
  @Test
  void cartItemsForMember() {

    var result = given()
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/carts")
        .then().log().all()
        .extract();

    assertThat(cartService.cartProducts(member).size()).isEqualTo(1);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @DisplayName("로그인한 유저의 장바구니에 상품을 추가한다.")
  @Test
  void addItemToCart() {
    CartCreateDto dto = new CartCreateDto(2L, 1);

    System.out.println(cartService.cartProducts(member).size());
    var result = given()
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .body(dto)
        .when().post("/add-to-cart")
        .then().log().all()
        .extract();

    assertThat(cartService.cartProducts(member).size()).isEqualTo(2);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @DisplayName("로그인한 유저의 장바구니 상품을 삭제한다.")
  @Test
  void removeCart() {
    cartService.removeCart(1L, member);

    var result = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .delete("/cart/" + 1L)
        .then()
        .log().all()
        .extract();

    assertThat(cartRepository.findById(member.getId()).size()).isEqualTo(1);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }
}
