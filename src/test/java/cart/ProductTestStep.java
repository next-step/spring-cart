package cart;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @author minsukim on 2023/05/07
 * @project jwp-cart
 * @description
 */
public class ProductTestStep {

    public static ExtractableResponse<Response> 상품_전체목록_조회() {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_생성요청(Map<String, String> params) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/products/create")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_수정_요청(Map<String, String> params, int id) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().put("/products/update/{id}", id)
                .then().log().all()
                .extract();
    }

}
