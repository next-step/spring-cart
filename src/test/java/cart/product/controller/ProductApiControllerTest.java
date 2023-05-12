package cart.product.controller;

import cart.fixture.AssuredTest;
import cart.product.controller.dto.ProductResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cart.product.controller.ProductStep.상품_삭제_API_요청;
import static cart.product.controller.ProductStep.상품_삭제_응답_검증;
import static cart.product.controller.ProductStep.상품_생성_API_요청;
import static cart.product.controller.ProductStep.상품_생성_API_요청_응답변환;
import static cart.product.controller.ProductStep.상품_생성_실패_응답_검증;
import static cart.product.controller.ProductStep.상품_생성_응답_검증;
import static cart.product.controller.ProductStep.상품_수정_API_요청;
import static cart.product.controller.ProductStep.상품_수정_응답_검증;
import static cart.product.controller.ProductStep.상품_전체_조회_API_요청;
import static cart.product.controller.ProductStep.상품_전체_조회_응답_검증;

class ProductApiControllerTest extends AssuredTest {

    private static final String NAME = "테스트 상품";
    private static final String IMAGE = "테스트 이미지";
    private static final long PRICE = 1000L;

    @DisplayName("상품 생성 요청, 응답 테스트")
    @Test
    void createProduct() {
        // when
        ExtractableResponse<Response> extractableResponse = 상품_생성_API_요청(NAME, IMAGE, PRICE);

        // then
        상품_생성_응답_검증(extractableResponse, NAME, IMAGE, PRICE);
    }

    @DisplayName("상품 생성 실패 테스트")
    @Test
    void createProduct_fail() {
        // given
        long price = -1000L;

        // when
        ExtractableResponse<Response> extractableResponse = 상품_생성_API_요청(NAME, IMAGE, price);

        // then
        상품_생성_실패_응답_검증(extractableResponse);
    }

    @DisplayName("상품 수정 요청, 응답 테스트")
    @Test
    void updateProduct() {
        // given
        String expectedName = "수정 상품";
        String expectedImage = "수정 이미지";
        long expectedPrice = 2000L;

        ProductResponse productResponse = 상품_생성_API_요청_응답변환(NAME, IMAGE, PRICE);
        long productId = productResponse.getId();

        // when
        ExtractableResponse<Response> extractableResponse =
                상품_수정_API_요청(productId, expectedName, expectedImage, expectedPrice);

        // then
        상품_수정_응답_검증(extractableResponse, expectedName, expectedImage, expectedPrice);
    }

    @DisplayName("상품 삭제 요청, 응답 테스트")
    @Test
    void deleteProduct() {
        // given
        ProductResponse productResponse = 상품_생성_API_요청_응답변환(NAME, IMAGE, PRICE);
        long productId = productResponse.getId();

        // when
        ExtractableResponse<Response> extractableResponse =
                상품_삭제_API_요청(productId);

        // then
        상품_삭제_응답_검증(extractableResponse);
    }

    @DisplayName("전체 상품 조회 요청, 응답 테스트")
    @Test
    void findProduct() {
        // given
        ProductResponse productResponse = 상품_생성_API_요청_응답변환(NAME, IMAGE, PRICE);

        // when
        ExtractableResponse<Response> extractableResponse = 상품_전체_조회_API_요청();

        // then
        상품_전체_조회_응답_검증(extractableResponse);
    }

}
