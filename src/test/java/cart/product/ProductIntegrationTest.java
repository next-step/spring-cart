package cart.product;

import cart.product.web.dto.CreateProduct;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 상품_추가에_성공한다(){
        CreateProduct.Request param = CreateProduct.Request.builder()
                .productName("생수")
                .price(1000)
                .imagePath("/test")
                .build();

        RestAssured
                .given().log().all()
                    .contentType(ContentType.JSON)
                    .body(param)
                .when()
                    .post("/product")
                .then()
                    .statusCode(200);
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
}
