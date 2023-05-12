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

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        // given
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto("상품A", "image.com/imageA", 10000);

        // when
        Long savedProductId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/api/v1/products")

        // then
                .then()
                .statusCode(HttpStatus.CREATED.value()).extract().as(Long.class);

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(savedProductId).get());
        assertThat(foundProduct.getName()).isEqualTo("상품A");
        assertThat(foundProduct.getImageUrl()).isEqualTo("image.com/imageA");
        assertThat(foundProduct.getPrice()).isEqualTo(10000);
    }

    @Test
    void update_valid() {
        // given
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto("상품B", "image.com/imageB", 20000);

        // when
        Long updatedProductId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().put("/api/v1/products/" + savedProduct.getId())

        // then
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(is(savedProduct.getId().toString())).extract().as(Long.class);

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(updatedProductId).get());
        assertThat(foundProduct.getName()).isEqualTo("상품B");
        assertThat(foundProduct.getImageUrl()).isEqualTo("image.com/imageB");
        assertThat(foundProduct.getPrice()).isEqualTo(20000);
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
        // given
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);

        // when
        Long deletedProductId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/v1/products/" + savedProduct.getId())

        // then
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(is(savedProduct.getId().toString())).extract().as(Long.class);

        assertThatThrownBy(() -> productDao.findById(deletedProductId).get())
                .isInstanceOf(NoSuchElementException.class);
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
