package cart.api.controller;

import cart.api.dto.ProductRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getAll() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void create() {
        ProductRequest request = new ProductRequest("food", 1000, "https://static.wtable.co.kr/image/production/service/recipe/2167/829d72bf-2523-4355-837b-33fd80cadf17.jpg?size=1024x1024");

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("유효하지 않은 생성 요청에는 예외를 반환한다.")
    @ParameterizedTest
    @MethodSource("invalidProducts")
    void createWithException(ProductRequest request) {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void update() {
        ProductRequest request = new ProductRequest("food", 1000, "https://static.wtable.co.kr/image/production/service/recipe/2167/829d72bf-2523-4355-837b-33fd80cadf17.jpg?size=1024x1024");

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put("/admin/{id}", 2)
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("유효하지 않은 수정 요청에는 예외를 반환한다.")
    @ParameterizedTest
    @MethodSource("invalidProducts")
    void updateWithException(ProductRequest request) {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put("/admin/{id}", 1)
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/admin/{id}", 1)
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    static Stream<ProductRequest> invalidProducts() {
        return Stream.of(
                new ProductRequest("", 1000, "http://a.com"),
                new ProductRequest("이름", -1000, "http://b.com"),
                new ProductRequest("이름", 2000, null)
        );
    }
}
