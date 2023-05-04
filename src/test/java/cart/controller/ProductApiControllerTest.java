package cart.controller;

import cart.fixture.AssuredTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cart.controller.ProductStep.상품_생성_API_요청;
import static cart.controller.ProductStep.상품_생성_응답_검증;

class ProductApiControllerTest extends AssuredTest {

    @DisplayName("상품 생성 요청, 응답 테스트")
    @Test
    void createProduct() {
        // given
        String name = "테스트 상품";
        String image = "테스트 이미지";
        long price = 1000L;

        // when
        ExtractableResponse<Response> extractableResponse = 상품_생성_API_요청(name, image, price);

        // then
        상품_생성_응답_검증(extractableResponse, name, image, price);
    }

}
