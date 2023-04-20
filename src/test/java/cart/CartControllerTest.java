package cart;

import cart.domain.Cart;
import io.restassured.RestAssured;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @LocalServerPort
    private int port;

    private String base64;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        this.base64 = Base64.encodeBase64String("cyan@springcart.com:password1".getBytes());
    }

    @DisplayName("cartListTest")
    @Test
    void selectCart() {

        var result = given()
                .header("Authorization", "Basic " + base64)
                .when()
                .post("/cart/list")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    @DisplayName("addCartTest")
    @Test
    void addCart() {

        Cart cart = new Cart(1, 1, "치킨", 10000, "url");

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + base64)
                .when()
                .body(cart)
                .post("/cart/add")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @DisplayName("removeCartTest")
    @Test
    void removeCart() {

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + base64)
                .params("id",Long.valueOf("1"))
                .when()
                .delete("/cart/remove")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());

    }

}
