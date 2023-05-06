package cart.product;

import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import cart.product.web.dto.CreateProduct;
import cart.product.web.dto.DeleteProduct;
import cart.product.web.dto.UpdateProduct;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {
    @LocalServerPort
    int port;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 상품_추가에_성공한다(){
        CreateProduct.Request param = CreateProduct.Request.builder()
                .name("생수")
                .price(1000)
                .image("/test")
                .build();

        ExtractableResponse<Response> createdProduct = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/product")
                .then()
                .statusCode(200)
                .extract();

        Product product = productRepository.findById(createdProduct.jsonPath()
                .getLong("id"));
        assertAll(
                () -> Assertions.assertThat(product.getName())
                        .isEqualTo(createdProduct.jsonPath().get("name")),
                () -> Assertions.assertThat(product.getPrice())
                         .isEqualTo(createdProduct.jsonPath().get("price")),
                () -> Assertions.assertThat(product.getImage())
                        .isEqualTo(createdProduct.jsonPath().get("image"))
        );

    }

    @Test
    void 상품_id로_상품_하나_조회에_성공한다() {
        Long id = 1L;

        RestAssured
                .given().log().all()
                .pathParam("id", id)
                .when()
                .get("/product/{id}")
                .then().log().all()
                .statusCode(200)
                .body("id", response -> equalTo(1)) // equalTo
                .body("id", is(1))                    // is
                .body("name", is("치킨"))
                .body("price", is(10000))
                .body("image", is("/images/chicken.jpeg"));
    }

    @Test
    void 모든_상품_리스트_조회에_성공한다(){
        RestAssured
                .given().log().all()
                .when()
                .get("/product")
                .then()
                .statusCode(200)
                .body("size()", is(3))
                .body("[0].id", is(1))
                .body("[0].name", is("치킨"))
                .body("[1].id", is(2))
                .body("[1].name", is("샐러드"))
                .body("[2].id", is(3))
                .body("[2].name", is("피자"));
    }

    @Test
    void id_1번_상품의_가격을_수정한다(){
        // given
        Long id = 1L;
        UpdateProduct.Request request = UpdateProduct.Request.builder()
                .name("치킨")
                .image("/images/chicken.jpeg")
                .price(12000)
                .build();

        RestAssured
                .given().log().all()
                .pathParam("id", id)
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/product/{id}")
                .then()
                .statusCode(200);

        Product actualProduct = productRepository.findById(id);
        assertThat(actualProduct.getPrice()).isEqualTo(request.getPrice());
    }

    @Test
    void id_1번_상품을_삭제한다() {
        DeleteProduct.Request request = new DeleteProduct.Request(1L);

        RestAssured
                .given().log().all()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/product/delete")
                .then().log().all()
                .statusCode(200);

    }
}
