package cart.cart.controller;

import cart.cart.controller.dto.CartProductResponse;
import cart.product.controller.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class CartStep {

    public static ExtractableResponse<Response> 장바구니_추가_API_요청(String email, String password, long productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", String.valueOf(productId));

        return RestAssured
                .given().log().all()
                .auth()
                .preemptive()
                .basic(email, password)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/carts")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_조회_API_요청(String email, String password, long productId) {
        return RestAssured
                .given().log().all()
                .auth()
                .preemptive()
                .basic(email, password)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/carts")
                .then().log().all()
                .extract();
    }

    public static void 장바구니_조회_API_응답_검증(ExtractableResponse<Response> extractableResponse) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<CartProductResponse> response = extractableResponse.jsonPath().getList(".", CartProductResponse.class);
        CartProductResponse cartProductResponse = response.get(0);
        assertThat(cartProductResponse.getId()).isNotNull();
    }

    public static ExtractableResponse<Response> 장바구니_삭제_API_요청(String email, String password, long productId) {
        return RestAssured
                .given().log().all()
                .auth()
                .preemptive()
                .basic(email, password)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/carts/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static void 장바구니_삭제_API_응답_검증(ExtractableResponse<Response> extractableResponse) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

}
