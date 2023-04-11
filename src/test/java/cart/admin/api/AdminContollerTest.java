package cart.admin.api;

import cart.RestAssuredApiSteps;
import cart.admin.application.AdminService;
import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import cart.product.api.ProductCartController;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminContollerTest {

    RestAssuredApiSteps<Product> productRestAssuredApiSteps;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        productRestAssuredApiSteps = new RestAssuredApiSteps<>();
    }

    @Autowired
    private AdminProductDao adminProductDao;

    @DisplayName("관리자페이지 상품 조회 테스트")
    @Test
    public void getAdminProducts() {
        var result = productRestAssuredApiSteps.getUrl("/admin/products/");

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("관리자페이지 상품 생성 테스트")
    @Test
    void postAdminProduct() {

        Product product = new Product("lucas", "url", 5000);

        var result = productRestAssuredApiSteps.createUrlWithBody("/admin/products/", product);

        assertThat(result.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("관리자페이지 상품 업데이트 테스트")
    @Test
    void putAdminProduct() {

        Product product = adminProductDao.insertProduct(new Product("test", "url", 55));

        product.setName("change");
        product.setPrice(77);

        var result = productRestAssuredApiSteps.updateUrlWithBody("/admin/products/", product);

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }


    @DisplayName("관리자페이지 상품 삭제 테스트")
    @Test
    void deleteAdminProduct() {
        Product product = adminProductDao.insertProduct(new Product("test", "url", 55));

        var result = given()
                .pathParam("productId", product.getId())
                .when()
                .delete("/admin/products/{productId}")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @DisplayName("관리자페이지 상품 삭제 테스트 삭제 ID 없을 경우")
    @Test
    void deleteAdminProductExceptionTest() {
        Product product = adminProductDao.insertProduct(new Product("test", "url", 55));

        var result = given()
                .when()
                .delete("/admin/products/")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

}
