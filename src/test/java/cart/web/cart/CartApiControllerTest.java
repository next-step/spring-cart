package cart.web.cart;

import cart.domain.cart.Cart;
import cart.infrastructure.dao.CartDao;
import cart.service.cart.CartService;
import cart.web.cart.dto.CartAddRequestDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static cart.service.user.UserServiceTest.USER_1;
import static cart.service.user.UserServiceTest.USER_2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@Sql(scripts = "classpath:cartSampleData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CartService cartService;
    @Autowired
    private CartDao cartDao;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 정상_모든_장바구니_아이템을_조회한다() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(2L));
        cartService.add(USER_2, new CartAddRequestDto(1L));
        cartService.add(USER_1, new CartAddRequestDto(1L));

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization",
                        authorizationHeader(USER_1.getEmail(), USER_1.getPassword()))
                .when().get("/api/v1/carts")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.response().jsonPath().getInt("size()")).isEqualTo(2);
        assertThat(response.response().jsonPath().getString("[0].name")).isEqualTo("상품B");
        assertThat(response.response().jsonPath().getString("[1].name")).isEqualTo("상품A");
    }

    @Test
    void 비정상_모든_장바구니_아이템을_조회한다_유저_정보가_없는_경우() {
        // given, when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/carts")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_장바구니_아이템을_추가한다() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(3L);

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization",
                        authorizationHeader(USER_1.getEmail(), USER_1.getPassword()))
                .body(requestDto)
                .when().post("/api/v1/carts")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(response.as(Long.class)).get());
        assertThat(foundCart.getProduct().getId()).isEqualTo(3L);
    }

    @Test
    void 비정상_장바구니_아이템을_추가한다_유저_정보가_없는_경우() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(3L);

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/api/v1/carts")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_장바구니_아이템을_삭제한다() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(1L));

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization",
                        authorizationHeader(USER_1.getEmail(), USER_1.getPassword()))
                .when().delete("/api/v1/carts/1")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(cartDao.findById(response.as(Long.class))).isEmpty();
    }

    @Test
    void 비정상_장바구니_아이템을_삭제한다_유저_정보가_없는_경우() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(1L));

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/v1/carts/1")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 비정상_장바구니_아이템을_삭제한다_다른_유저의_아이템인_경우() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(1L));

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization",
                        authorizationHeader(USER_2.getEmail(), USER_2.getPassword()))
                .when().delete("/api/v1/carts/1")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    private String authorizationHeader(String email, String password) {
        String credential = new String(Base64.encodeBase64((email + ":" + password).getBytes()));
        return "BASIC " + credential;
    }

}
