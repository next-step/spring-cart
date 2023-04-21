package cart.admin.api;

import cart.RestAssuredApiSteps;
import cart.ProductAdminService;
import cart.dao.ProductDao;
import cart.domain.Product;
import cart.controller.ProductAdminController;
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
public class AdminControllerTest {

    RestAssuredApiSteps<Product> productRestAssuredApiSteps;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        productRestAssuredApiSteps = new RestAssuredApiSteps<>();
    }

    @Autowired
    private ProductDao adminProductDao;

    @DisplayName("관리자페이지 상품 조회 테스트")
    @Test
    public void getAdminProducts() {
        var result = productRestAssuredApiSteps.getUrl("/admin/products/");
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("상품 추가하기 테스트")
    @Test
    void postAdminProduct() {
        Product product = new Product("lucas", "url", 5000);

        var result = productRestAssuredApiSteps.createUrlWithBody("/admin/products/", product);
        assertThat(result.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("상품 정보 업데이트(가격) 테스트")
    @Test
    void putAdminProduct() {

        Product product = adminProductDao.insertProduct(new Product("iPhone15Pro", "http://wwww.apple.co.kr/index.html", 120000));

        product.setPrice(150000);

        var result = productRestAssuredApiSteps.updateUrlWithBody("/admin/product", product);

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
    }


}