package cart;

import cart.domain.Cart;
import cart.repository.CartRepository;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.rootPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartIntegrationTest {
    private static final Header MEMBER1_AUTHORIZATION_VALUE = new Header(HttpHeaders.AUTHORIZATION, "Basic dGVzdEBuYXZlci5jb206cGFzc3dvcmQhIQ==");
    private static final Header AUTHORIZATION_WRONG_VALUE = new Header(HttpHeaders.AUTHORIZATION, "fail_value");
    private final static long MEMBER1_ID = 1;
    private final static long MEMBER2_ID = 2;

    @LocalServerPort
    private int port;

    @Autowired
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @BeforeEach
    void before() {
        cartRepository.save(new Cart(1L, MEMBER1_ID, 1));
        cartRepository.save(new Cart(2L, MEMBER1_ID, 2));
        cartRepository.save(new Cart(3L, MEMBER2_ID, 2));
    }

    @AfterEach
    void after() {
        cartRepository.deleteAll(MEMBER1_ID);
        cartRepository.deleteAll(MEMBER2_ID);
    }


    @Test
    public void postCart() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/cart/1")
                .then()
                .extract();

        assertThat(cartRepository.findAll(MEMBER1_ID)).hasSize(3);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void postCartFailAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION_WRONG_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/cart/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void postCartNoneAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/cart/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void getCart() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/cart")
                .then().log().all()
                .assertThat().body(rootPath, hasSize(2))
                .assertThat().body("name", containsInAnyOrder("단짠단짠 피자", "BBQ 3만원치킨"))
                .assertThat().body("id", containsInAnyOrder(1, 2))
                .assertThat().body("image", containsInAnyOrder("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU"))
                .assertThat().body("price", containsInAnyOrder(25000, 30000))
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getCartFailAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION_WRONG_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/cart")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void getCartNoneAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/cart")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void deleteCart() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/cart/1")
                .then()
                .extract();

        assertThat(cartRepository.findAll(MEMBER1_ID)).hasSize(1);

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteCartFailAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION_WRONG_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/cart/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void deleteCartNoneAuth() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/cart/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
