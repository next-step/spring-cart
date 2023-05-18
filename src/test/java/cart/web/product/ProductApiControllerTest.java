package cart.web.product;

import cart.domain.product.Product;
import cart.infrastructure.dao.ProductDao;
import cart.web.product.dto.ProductSaveRequestDto;
import cart.web.product.dto.ProductUpdateRequestDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApiControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 상품을_저장한다() {
        // given
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto("상품A", "image.com/imageA", 10000);

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().post("/api/v1/products")
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(response.as(Long.class)).get());
        assertThat(foundProduct.getName()).isEqualTo("상품A");
        assertThat(foundProduct.getImageUrl()).isEqualTo("image.com/imageA");
        assertThat(foundProduct.getPrice()).isEqualTo(10000);
    }

    @Test
    void 정상_상품을_수정한다() {
        // given
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto("상품B", "image.com/imageB", 20000);

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestDto)
                .when().put("/api/v1/products/" + savedProduct.getId())
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().asString()).isEqualTo(savedProduct.getId().toString());

        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(response.as(Long.class)).get());
        assertThat(foundProduct.getName()).isEqualTo("상품B");
        assertThat(foundProduct.getImageUrl()).isEqualTo("image.com/imageB");
        assertThat(foundProduct.getPrice()).isEqualTo(20000);
    }

    @Test
    void 비정상_상품을_수정한다_존재하지_않는_상품인_경우() {
        // given, when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ProductUpdateRequestDto("", "", 0))
                .when().put("/api/v1/products/" + 1)
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void 정상_상품을_삭제한다() {
        // given
        Product givenProduct = Product.builder()
                .name("상품A")
                .imageUrl("image.com/imageA")
                .price(10000)
                .build();
        Product savedProduct = productDao.insert(givenProduct);

        // when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/api/v1/products/" + savedProduct.getId())
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().asString()).isEqualTo(savedProduct.getId().toString());


        assertThatThrownBy(() -> productDao.findById(response.as(Long.class)).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 정상_상품을_삭제한다_존재하지_않는_상품인_경우() {
        // given, when
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ProductUpdateRequestDto("", "", 0))
                .when().delete("/api/v1/products/" + 1)
                .then().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
