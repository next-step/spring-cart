package cart.presentation;

import static org.assertj.core.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

  @LocalServerPort private int port;

  @BeforeEach
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  @DisplayName("상품 등록 API")
  public void register() {
    // when
    ExtractableResponse<Response> response = createProduct();

    // then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.header(HttpHeaders.LOCATION)).isNotEmpty();
  }

  @Test
  @DisplayName("상품 리스트 조회 API")
  public void getAllProducts() {
    // given
    createProduct();
    createProduct();

    // when
    ExtractableResponse<Response> response =
        RestAssured.when().get("/products").then().log().all().extract();

    // then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.jsonPath().getList("$").size()).isEqualTo(2);
  }

  @Test
  @DisplayName("상품 수정 API")
  public void modifyProduct() {
    // given
    Map<String, Object> body = new HashMap<>();
    body.put("name", "Product02");
    body.put("price", 20000);
    body.put("imageUrl", "https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg");
    
    createProduct();

    // when
    ExtractableResponse<Response> response =
        RestAssured.given()
            .log()
            .all()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .put("/products/{id}", 1)
            .then()
            .log()
            .all()
            .extract();

    // then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat((String) response.jsonPath().get("name")).isEqualTo(body.get("name"));
    assertThat((Integer) response.jsonPath().get("price")).isEqualTo(body.get("price"));
  }

  @Test
  @DisplayName("상품 삭제 API")
  public void removeProduct() {
    // given
    createProduct();

    // when
    ExtractableResponse<Response> response =
        RestAssured.when().delete("/products/{id}", 1).then().extract();

    // then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  private ExtractableResponse<Response> createProduct() {
    Map<String, Object> body = new HashMap<>();
    body.put("name", "Product01");
    body.put("price", 10000);
    body.put("imageUrl", "https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg");

    return RestAssured.given()
        .contentType(ContentType.JSON)
        .body(body)
        .when()
        .post("/products")
        .then()
        .extract();
  }
}
