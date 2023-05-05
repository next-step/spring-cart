package cart.product;

import cart.product.web.dto.CreateProduct;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
}
