package cart.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import cart.domain.Cart;
import cart.dto.CartCreateDto;
import cart.repository.CartRepository;
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
class CartServiceTest {

  private static final Header MEMBER1_AUTHORIZATION_VALUE = new Header(HttpHeaders.AUTHORIZATION,
      "Basic YUBhLmNvbTpwYXNzd29yZDE=");
  private final static long MEMBER1_ID = 1L;
  @LocalServerPort
  int port;

  @Autowired
  private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @AfterEach
  void afterEach() {
    List<Cart> cartList = cartRepository.findById(MEMBER1_ID);
    for (Cart cart : cartList) {
      Long cartId = cart.getId();
      cartRepository.removeCart(cartId,MEMBER1_ID); // 카트 아이디를 기반으로 카트 삭제
    }
  }


  @DisplayName("로그인한 유저의 장바구니 리스트를 출력한다.")
  @Test
  void cartItemsForMember() {
    cartRepository.addProduct(MEMBER1_ID, 1L);
    var result = given()
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/carts")
        .then().log().all()
        .extract();

    assertThat(cartRepository.findById(MEMBER1_ID).size()).isEqualTo(1);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @DisplayName("장바구니에 상품을 추가한다.")
  @Test
  void addItemToCart() {
    CartCreateDto dto = new CartCreateDto(2L, 1);

    var result = given()
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .body(dto)  // JSON 형식의 요청 본문을 설정
        .when().post("/add-to-cart")
        .then().log().all()
        .extract();

    assertThat(cartRepository.findById(MEMBER1_ID).size()).isEqualTo(1);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void removeCart() {
    cartRepository.addProduct(MEMBER1_ID, 1L);
    var cartList = cartRepository.findById(MEMBER1_ID);
    Long cartId = cartList.get(0).getId(); // 첫 번째 장바구니 아이템의 ID를 가져옴

    var result = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .header(MEMBER1_AUTHORIZATION_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .delete("/cart/" + cartId) // cartId를 URL 경로에 동적으로 설정
        .then()
        .log().all()
        .extract();

    assertThat(cartRepository.findById(MEMBER1_ID).size()).isEqualTo(0); // 장바구니가 비어있는지 확인
    assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }
}
