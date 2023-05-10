package cart.cart.controller;

import cart.fixture.AssuredTest;
import cart.product.controller.dto.ProductResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static cart.cart.controller.CartStep.장바구니_삭제_API_요청;
import static cart.cart.controller.CartStep.장바구니_삭제_API_응답_검증;
import static cart.cart.controller.CartStep.장바구니_조회_API_요청;
import static cart.cart.controller.CartStep.장바구니_조회_API_응답_검증;
import static cart.cart.controller.CartStep.장바구니_추가_API_요청;
import static cart.member.controller.MemberStep.유저_생성_API_요청;
import static cart.product.controller.ProductStep.상품_생성_API_요청;
import static org.assertj.core.api.Assertions.assertThat;

class CartApiControllerTest extends AssuredTest {

    private static final String EMAIL = "mail";
    private static final String PASSWORD = "password";

    @DisplayName("장바구니 추가 요청, 응답 테스트")
    @Test
    void createProduct() {
        // given
        유저_생성_API_요청(EMAIL, PASSWORD);

        // when
        ExtractableResponse<Response> extractableResponse = 장바구니_추가_API_요청(EMAIL, PASSWORD, 1L);

        // then
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("장바구니 조회 요청, 응답 테스트")
    @Test
    void getCart() {
        // given
        유저_생성_API_요청(EMAIL, PASSWORD);
        ProductResponse productResponse = 상품_생성_API_요청("상품", "이미지", 1000L)
                .as(ProductResponse.class);
        장바구니_추가_API_요청(EMAIL, PASSWORD, productResponse.getId());

        // when
        ExtractableResponse<Response> extractableResponse = 장바구니_조회_API_요청(EMAIL, PASSWORD, productResponse.getId());

        // then
        장바구니_조회_API_응답_검증(extractableResponse);
    }

    @DisplayName("장바구니 삭제 요청, 응답 테스트")
    @Test
    void deleteCartProduct() {
        // given
        유저_생성_API_요청(EMAIL, PASSWORD);
        ProductResponse productResponse = 상품_생성_API_요청("상품", "이미지", 1000L)
                .as(ProductResponse.class);
        장바구니_추가_API_요청(EMAIL, PASSWORD, productResponse.getId());

        // when
        ExtractableResponse<Response> extractableResponse = 장바구니_삭제_API_요청(EMAIL, PASSWORD, productResponse.getId());

        // then
        장바구니_삭제_API_응답_검증(extractableResponse);
    }

}
