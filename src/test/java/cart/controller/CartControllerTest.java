package cart.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import cart.domain.Member;
import cart.dto.CartCreateDto;
import cart.dto.CartDetailDto;
import cart.exception.NotFoundEntityException;
import cart.repository.CartRepository;
import cart.service.CartService;
import cart.service.MemberService;
import io.restassured.RestAssured;
import java.util.List;
import javax.security.sasl.AuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

@DisplayName("장바구니 컨트롤러 테스트")
@Sql(scripts = {"classpath:CartSampleData.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

  @LocalServerPort
  int port;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private MemberService memberService;
  Member member;

  @BeforeEach
  void setUp() throws NotFoundEntityException {
    RestAssured.port = port;
    member = memberService.findByEmail("a@a.com");
  }

  @DisplayName("로그인한 유저의 장바구니 리스트를 출력한다.")
  @Test
  void cartItemsForMember() {
    var result = given()
        .auth().preemptive().basic(member.getEmail(), member.getPassword())
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/carts")
        .then().log().all()
        .extract();

    List<CartDetailDto> list = cartService.cartProducts(member);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    Assertions.assertNotNull(list);
  }

  @DisplayName("비로그인한 유저의 장바구니 리스트를 요청하면 AuthenticationException 발생한다.")
  @Test
  void getCartsWithNoLogin() {
    given()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/carts")
        .then()
        .log().all()
        .statusCode(HttpStatus.UNAUTHORIZED.value());

    assertThatThrownBy(() -> {throw new AuthenticationException();
    }).isInstanceOf(AuthenticationException.class);
  }

  @DisplayName("로그인한 유저의 장바구니에 상품을 추가한다.")
  @Test
  void addItemToCart() {
    CartCreateDto createDto = CartCreateDto.builder()
        .productId(1L)
        .count(1)
        .build();

    System.out.println(cartService.cartProducts(member).size());
    var result = given()
        .auth().preemptive().basic(member.getEmail(), member.getPassword())
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .body(createDto)
        .when().post("/carts/add-to-cart")
        .then().log().all()
        .extract();

    assertThat(cartService.cartProducts(member).size()).isEqualTo(2);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @DisplayName("로그인한 유저의 장바구니 상품을 삭제한다.")
  @Test
  void removeCart() {
    List<CartDetailDto> list = cartService.cartProducts(member);

    var result = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .auth().preemptive().basic(member.getEmail(), member.getPassword())
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .delete("/carts/" + list.get(0).getId())
        .then()
        .log().all()
        .extract();

    assertThat(cartRepository.findById(member.getId()).size()).isEqualTo(0);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }
}
