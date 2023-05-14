package cart;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    private final ProductRequest productRequest = new ProductRequest("bbq", "images/sample.jpeg", 20000);

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("전체 상품 목록을 조회한다.")
    @Test
    public void getProducts() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/product")
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("상품을 생성한다.")
    @Test
    public void addProducts() {
        var productRequest = new ProductRequest("bbq", "images/sample.jpeg", 20000);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productRequest)
                .when()
                .post("/product")
                .then()
                .extract();

        var created = result.body().as(ProductResponse.class);
        assertAll(
                () -> assertThat(created.getId()).isNotNull(),
                () -> assertThat(created.getName()).isEqualTo(productRequest.getName()),
                () -> assertThat(created.getPrice()).isEqualTo(productRequest.getPrice()),
                () -> assertThat(created.getImageUrl()).isEqualTo(productRequest.getImageUrl())
        );
    }

    @DisplayName("상품을 수정한다.")
    @Test
    public void updateProducts() {
        var oldProduct = createProduct(productRequest);
        var updateRequest = new ProductRequest("bhc", "images/sample.jpeg", 10000);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateRequest)
                .when()
                .put("/product/" + oldProduct.getId())
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());

        var updated = result.body().as(ProductResponse.class);
        assertAll(
                () -> assertThat(updated.getId()).isEqualTo(oldProduct.getId()),
                () -> assertThat(updated.getName()).isEqualTo(updateRequest.getName()),
                () -> assertThat(updated.getPrice()).isEqualTo(updateRequest.getPrice()),
                () -> assertThat(updated.getImageUrl()).isEqualTo(updateRequest.getImageUrl())
        );
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("상품을 제거한다.")
    @Test
    public void deleteProducts() {
        var savedProduct = createProduct(productRequest);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/product/" + savedProduct.getId())
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static ProductResponse createProduct(ProductRequest productRequest) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(productRequest)
                .when()
                .post("/product")
                .then()
                .extract()
                .as(ProductResponse.class);
    }
}
