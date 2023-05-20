package cart.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommonViewControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void index_페이지를_반환한다() {
        RestAssured
                .when().get("/")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void admin_페이지를_반환한다() {
        RestAssured
                .when().get("/admin")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void settings_페이지를_반환한다() {
        RestAssured
                .when().get("/settings")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void cart_페이지를_반환한다() {
        RestAssured
                .when().get("/cart")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
