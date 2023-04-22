package cart;

import cart.domain.Product;
import cart.value.ProductRequest;
import cart.value.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
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
class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("상품을 등록한다")
    void createProduct() {
        // given, when
        var result = create();

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("상품 목록을 조회한다")
    void getProducts() {
        // given, when
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/products")
                .then()
                .extract();

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("등록된 상품을 수정한다")
    void updateProduct() {
        // given
        var response = create().as(ProductResponse.class);
        var productId = response.getId();
        var expected = ProductResponse.from(new Product(5000, "카페라떼", "bada.com"));

        // when
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(new ProductRequest(5000, "카페라떼", "bada.com"))
                .put("/products/" + productId)
                .then()
                .extract();
        var updated = result.body().as(ProductResponse.class);

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(updated).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);

    }

    @Test
    @DisplayName("사용자 정보가 없으면 장바구니에 담을 수 없다")
    void addCart() {
        // given
         var response = create().as(ProductResponse.class);
         var productId = response.getId();

         // when
         var result = given()
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .when()
                 .put("/products/" + productId + "/carts")
                 .then()
                 .extract();

         // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("상품을 삭제한다")
    void deleteProduct() {
        // given
        var response = create().as(ProductResponse.class);
        var productId = response.getId();

        // when
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/products/" + productId)
                .then()
                .extract();

        // then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private ExtractableResponse<Response> create() {
        var request = new ProductRequest(3000, "아메리카노", "bada.com");

        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(request)
                .post("/products")
                .then()
                .extract();
    }
}
