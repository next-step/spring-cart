package cart;

import cart.domain.Cart;
import cart.repository.CartRepository;
import io.restassured.http.Header;
import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartIntegrationTest {
    private static final Header MEMBER1_AUTHORIZATION_VALUE = new Header(HttpHeaders.AUTHORIZATION, "Basic c2VubmFAc3ByaW5nY2FydC5jb206c2VubmFAcGFzc3dvcmQ=");
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
        cartRepository.addCart(new Cart(MEMBER1_ID, 1L));
        cartRepository.addCart(new Cart(MEMBER1_ID, 2L));
        cartRepository.addCart(new Cart(MEMBER2_ID,2L));
    }

    @AfterEach
    void after() {
        cartRepository.deleteAll(MEMBER1_ID);
        cartRepository.deleteAll(MEMBER2_ID);
    }

    @Test
    public void 사용자_장바구니_조회() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/cart/show")
                .then()
                .extract();

        assertThat(cartRepository.getCart(MEMBER1_ID).size()).isEqualTo(2);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 사용자_장바구니_추가() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/cart/add/3")
                .then()
                .extract();

        assertThat(cartRepository.getCart(MEMBER1_ID).size()).isEqualTo(3);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 사용자_장바구니_삭제() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(MEMBER1_AUTHORIZATION_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/cart/delete/2")
                .then()
                .extract();

        assertThat(cartRepository.getCart(MEMBER1_ID).size()).isEqualTo(1);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
