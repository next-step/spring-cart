package cart.acceptance;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ProductSteps {

    public static ExtractableResponse<Response> 상품_조회_단건_요청(Long productId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_목록_요청(String path) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_생성_요청(ProductRequest productRequest) {
        Map<String, String> params = new HashMap<>();
        params.put("name", productRequest.getName());
        params.put("image", productRequest.getImage());
        params.put("price", String.valueOf(productRequest.getPrice()));
        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/products")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_수정_요청(Long productId, ProductEditRequest productEditRequest) {
        Map<String, String> params = new HashMap<>();
        params.put("name", productEditRequest.getName());
        params.put("image", productEditRequest.getImage());
        params.put("price", String.valueOf(productEditRequest.getPrice()));
        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch("/products/{productId}", productId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_삭제_요청(Long productId) {
        return RestAssured.given().log().all()
                .when()
                .delete("/products/{productId}", productId)
                .then().log().all()
                .extract();
    }
}
