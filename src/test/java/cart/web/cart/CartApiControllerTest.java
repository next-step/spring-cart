package cart.web.cart;

import cart.domain.cart.Cart;
import cart.infrastructure.dao.CartDao;
import cart.web.cart.dto.CartAddRequestDto;
import io.restassured.RestAssured;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CartDao cartDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        // reset tables
        jdbcTemplate.update("DELETE FROM cart");
        jdbcTemplate.update("DELETE FROM product");
        jdbcTemplate.update("DELETE FROM users");

        // user insert
        jdbcTemplate.update("INSERT INTO users(email, password) VALUES('a@a.com', 'passwordA')"); // id = 1
        jdbcTemplate.update("INSERT INTO users(email, password) VALUES('b@b.com', 'passwordB')"); // id = 2

        // product insert
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품A', 'image.com/imageA', 10000)"); // id = 1
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품B', 'image.com/imageB', 20000)"); // id = 2
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품C', 'image.com/imageC', 30000)"); // id = 3

        // cart insert
        jdbcTemplate.update("INSERT INTO cart(user_id, product_id) VALUES(1, 1)");
        jdbcTemplate.update("INSERT INTO cart(user_id, product_id) VALUES(1, 2)");
    }

    @Test
    void 정상_모든_장바구니_아이템을_조회한다() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .when().get("/api/v1/carts")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("[0].name", is("상품A"))
                .body("[1].name", is("상품B"));
    }

    @Test
    void 비정상_모든_장바구니_아이템을_조회한다_유저_정보가_없는_경우() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/carts")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_장바구니_아이템을_추가한다() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(3L);

        // when
        Long addedCartId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .body(requestDto)
                .when().post("/api/v1/carts")

        // then
                .then()
                .statusCode(HttpStatus.CREATED.value()).extract().as(Long.class);

        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(addedCartId).get());
        assertThat(foundCart.getProductId()).isEqualTo(3L);
    }

    @Test
    void 비정상_장바구니_아이템을_추가한다_유저_정보가_없는_경우() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/carts")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_장바구니_아이템을_삭제한다() {
        // given, when
        Long deletedCartId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .when().delete("/api/v1/carts/1")

        // then
                .then()
                .statusCode(HttpStatus.OK.value()).extract().as(Long.class);

        assertThat(cartDao.findById(deletedCartId)).isEmpty();
    }

    @Test
    void 비정상_장바구니_아이템을_삭제한다_유저_정보가_없는_경우() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/v1/carts/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 비정상_장바구니_아이템을_삭제한다_다른_유저의_아이템인_경우() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("b@b.com", "passwordB"))
                .when().delete("/api/v1/carts/1")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    private String authorizationHeader(String email, String password) {
        String credential = new String(Base64.encodeBase64((email + ":" + password).getBytes()));
        return "BASIC " + credential;
    }

}
