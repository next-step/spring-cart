package cart.controller;

import static org.hamcrest.Matchers.is;

import cart.dto.ProductCreateDto;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("Controller")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

  @LocalServerPort
  int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @DisplayName("모든 메뉴를 호출하는 api")
  @Test
  void showUser() {
    RestAssured.given().log().all()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when().get("/products")
        .then().log().all()
        .statusCode(HttpStatus.OK.value())
        .body("size()", is(3));
  }

  @DisplayName("메뉴 추가 api")
  @Test
  void addProduct() {
    ProductCreateDto createDto = new ProductCreateDto("coffee","coffee.jpg",new BigDecimal(1000));


    RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(createDto)
        .when().post("/products")
        .then().log().all()
        .statusCode(HttpStatus.OK.value());
  }
}