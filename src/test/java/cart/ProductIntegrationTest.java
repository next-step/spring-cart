package cart;

import cart.dto.ProductDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void testAdmin() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void insertProduct() {
        ProductDto product = new ProductDto(
                1,
                "치킨2",
                10000,
                "https://images.unsplash.com/photo-1626082896492-766af4eb6501?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80"
        );

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("/createProduct")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void updateProduct() {
        ProductDto product = new ProductDto(
                1,
                "칙힌",
                1000,
                "https://images.unsplash.com/photo-1626082896492-766af4eb6501?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80"
        );

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("/changeProduct")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @Test
    public void deleteProduct() {
        ProductDto product = new ProductDto(
                1,
                "칙힌",
                1000,
                "https://images.unsplash.com/photo-1626082896492-766af4eb6501?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80"
        );

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("/removeProduct")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

}
