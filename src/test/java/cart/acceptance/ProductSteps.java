package cart.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ProductSteps {

    public static ExtractableResponse<Response> 상품_목록_요청(String path) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 상품_생성_요청(String name, String image, String price) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("image", image);
        params.put("price", price);
        return RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/products")
                .then().log().all()
                .extract();
    }
}
