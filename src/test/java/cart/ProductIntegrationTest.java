package cart;

import cart.cartItem.CartItemService;
import cart.product.model.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void getProductsTest() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void createProductTest() {

        var product = new Product();
        product.setName("orange");
        product.setImage("image/orange.jpg");
        product.setPrice(1000);

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .post("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void updateProductTest() {

        var product = new Product();
        product.setId(Long.valueOf(2));
        product.setName("banana");
        product.setImage("image/banana.jpg");
        product.setPrice(2000);

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .put("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteProduct() {

        var product = new Product();
        product.setName("orange");
        product.setImage("image/orange.jpg");
        product.setPrice(2000);

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(product)
                .delete("/admin/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void cartList() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers("Authorization", "Basic dGVzdDAxQGdtYWlsLmNvbToxMjM0")
                .when()
                .get("/cart/list")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void cartInsert() {

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers("Authorization", "Basic dGVzdDAxQGdtYWlsLmNvbToxMjM0")
                .when()
                .post("/cart/insert/1")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Autowired
    CartItemService cartItemService;

    @Test
    public void cartDelete() {

        var insertResult = cartItemService.cartInsert(Long.valueOf(1), Long.valueOf(1));

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers("Authorization", "Basic dGVzdDAxQGdtYWlsLmNvbToxMjM0")
                .when()
                .delete("/cart/delete/" + insertResult.getId())
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
