package cart.acceptance;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import cart.controller.dto.response.ProductResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import static cart.acceptance.ProductSteps.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("상품 관련 기능 인수테스트")
class ProductAcceptanceTest {

    private final ProductRequest createRequest = new ProductRequest("햄버거", "https://www.mcdonalds.co.kr/upload/product/pcList/1683098039703.png", 5000);
    private final ProductEditRequest editRequest = new ProductEditRequest("햄버거", "https://www.mcdonalds.co.kr/upload/product/pcList/1683098039703.png", 10000);

    @Test
    @DisplayName("/products 로 접근할 경우 상품 목록 조회에 성공한다.")
    void getProductsTest() {

        var response = 상품_목록_요청("/products");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("/products 와 다른 path로 접근할 경우 상품 목록 조회에 실패한다.")
    void getProductsExceptionTest() {

        var response = 상품_목록_요청("/peo");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("상품을 생성한다.")
    void postProduct() {

        var response = 상품_생성_요청(createRequest);

        var id = response.jsonPath().getLong("id");
        var productResponse = 상품_조회_단건_요청(id).as(ProductResponse.class);

        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(productResponse.getName()).isEqualTo(createRequest.getName()),
                () -> assertThat(productResponse.getImage()).isEqualTo(createRequest.getImage()),
                () -> assertThat(productResponse.getPrice()).isEqualTo(createRequest.getPrice())
        );

    }

    @Test
    @DisplayName("상품을 수정한다.")
    void editProduct() {

        var id = 상품_생성_요청(createRequest).jsonPath().getLong("id");

        var response = 상품_수정_요청(id, editRequest);

        var productResponse = 상품_조회_단건_요청(id).as(ProductResponse.class);
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(productResponse.getName()).isEqualTo(editRequest.getName()),
                () -> assertThat(productResponse.getImage()).isEqualTo(editRequest.getImage()),
                () -> assertThat(productResponse.getPrice()).isEqualTo(editRequest.getPrice())
                );
    }

    @Test
    @DisplayName("수정하려는 상품이 존재하지 않으면 수정에 실패한다.")
    void editProductException() {

        var response = 상품_수정_요청(3L, editRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("상품을 삭제한다.")
    void deleteProduct() {

        var id = 상품_생성_요청(createRequest).jsonPath().getLong("id");

        var response = 상품_삭제_요청(id);

        assertThat(response.statusCode()).isEqualTo(NO_CONTENT.value());
    }

    @Test
    @DisplayName("삭제하려는 상품이 존재하지 않으면 삭제에 실패한다.")
    void deleteProductException() {

        var response = 상품_삭제_요청(3L);

        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }

}
