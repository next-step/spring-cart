package cart.web;

import cart.domain.product.Product;
import cart.infrastructure.ProductDao;
import cart.web.dto.ProductSaveRequestDto;
import cart.web.dto.ProductUpdateRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.is;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApiControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ProductDao productDao;

    @Test
    void save() {
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto("상품A", "image.com/imageA", 10000);
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/api/v1/products")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void update_valid() {
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);

        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto("상품B", "image.com/imageB", 20000);
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().put("/api/v1/products/" + savedProduct.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(is(savedProduct.getId().toString()));
    }

    @Test
    void update_invalid_nonexistent() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ProductUpdateRequestDto("", "", 0))
                .when().put("/api/v1/products/" + 1)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete_valid() {
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/v1/products/" + savedProduct.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(is(savedProduct.getId().toString()));

    }

    @Test
    void delete_invalid_nonexistent() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ProductUpdateRequestDto("", "", 0))
                .when().delete("/api/v1/products/" + 1)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}