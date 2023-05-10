package cart.product.controller;

import cart.product.controller.dto.ProductResponse;
import cart.product.controller.dto.ProductsResponse;
import cart.exception.handler.dto.ErrorResponse;
import cart.exception.ErrorType;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class ProductStep {

    public static ExtractableResponse<Response> 상품_생성_API_요청(String name, String image, long price) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("image", image);
        params.put("price", String.valueOf(price));

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/products")
                .then().log().all()
                .extract();
    }

    public static ProductResponse 상품_생성_API_요청_응답변환(String name, String image, long price) {
        ExtractableResponse<Response> response = 상품_생성_API_요청(name, image, price);
        return response.jsonPath().getObject(".", ProductResponse.class);
    }

    public static void 상품_생성_응답_검증(ExtractableResponse<Response> extractableResponse,
                                   String name, String image, long price) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.OK.value());

        ProductResponse response = extractableResponse.jsonPath().getObject(".", ProductResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getImage()).isEqualTo(image);
        assertThat(response.getPrice().longValue()).isEqualTo(price);
    }

    public static void 상품_생성_실패_응답_검증(ExtractableResponse<Response> extractableResponse) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        ErrorResponse response = extractableResponse.jsonPath().getObject(".", ErrorResponse.class);
        assertThat(response.getErrorCode()).isEqualTo(ErrorType.INVALID_MONEY.name());
        assertThat(response.getMessage()).isEqualTo(ErrorType.INVALID_MONEY.getMessage());
    }

    public static ExtractableResponse<Response> 상품_수정_API_요청(long id, String name, String image, long price) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("image", image);
        params.put("price", String.valueOf(price));

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put("/products/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 상품_수정_응답_검증(ExtractableResponse<Response> extractableResponse,
                                   String name, String image, long price) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.OK.value());

        ProductResponse response = extractableResponse.jsonPath().getObject(".", ProductResponse.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getImage()).isEqualTo(image);
        assertThat(response.getPrice().longValue()).isEqualTo(price);
    }

    public static ExtractableResponse<Response> 상품_삭제_API_요청(long id) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().delete("/products/{id}", id)
                .then().log().all()
                .extract();
    }

    public static void 상품_삭제_응답_검증(ExtractableResponse<Response> extractableResponse) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static ExtractableResponse<Response> 상품_전체_조회_API_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/products")
                .then().log().all()
                .extract();
    }

    public static void 상품_전체_조회_응답_검증(ExtractableResponse<Response> extractableResponse) {
        assertThat(extractableResponse.statusCode()).isEqualTo(HttpStatus.OK.value());

        ProductsResponse response = extractableResponse.jsonPath().getObject(".", ProductsResponse.class);
        assertThat(response.getProducts()).isNotNull();
    }

}
