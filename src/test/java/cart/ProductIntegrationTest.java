package cart;

import cart.domain.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getProducts() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 상품_추가_테스트() {
        Product product = new Product(
                    "치킨",
                    "img/chicken.jpg",
                    15000
                     );

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("product/create")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    public void 상품_정보_업데이트_테스트() {
        Product initProduct = new Product(
                "치킨",
                "img/chicken.jpg",
                15000
        );
        Product product = new Product(
                Long.valueOf("1"),
                "짬뽕",
                "https://ak-d.tripcdn.com/images/1mi3j2224udq9w7pcE08F_W_670_10000.jpg?proc=source/trip",
                13000
        );

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(initProduct)
                .post("product/create")
                .then();


        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .put("product/update")
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 상품_삭제_테스트() {
        Product initProduct = new Product(
                "치킨",
                "img/chicken.jpg",
                15000
        );
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(initProduct)
                .post("product/create")
                .then();

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .params("id",Long.valueOf("1"))
                .delete("product/delete")
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
