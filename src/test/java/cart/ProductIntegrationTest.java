package cart;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import cart.domain.Product;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getProducts() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void updateProducts() {
        Product product = new Product("cheese", "https://www.cheese.com", 1000);
        Map<String, Object> params = new HashMap<>();
        params.put("product", product);

        var result = given()
                .body(params)
                // .param("product", product)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/product")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void createProducts() {
        Product product = new Product("cheese", "https://www.cheese.com", 1000);
        Map<String, Object> params = new HashMap<>();
        params.put("product", product);

        var result = given()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/product")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteCart() {
        var result = given()
                .header("Authorization", "Basic ZGF2aWRAa2FrYW9wYXlzZWMuY29tOnBhc3N3b3JkMQ==", "password1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/cart/2")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getCart() {
        var result = given()
                .header("Authorization", "Basic ZGF2aWRAa2FrYW9wYXlzZWMuY29tOnBhc3N3b3JkMQ==", "password1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/cart/list")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
