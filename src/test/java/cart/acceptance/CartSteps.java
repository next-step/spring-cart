package cart.acceptance;

import cart.controller.dto.request.CartAddRequest;
import cart.domain.User;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class CartSteps {

    public static ExtractableResponse<Response> 장바구니_목록_요청(String path) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_상품_추가_요청(User user, CartAddRequest cartAddRequest) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", String.valueOf(cartAddRequest.getProductId()));

        return RestAssured.given().log().all()
                .auth().preemptive().basic(user.getEmail(), user.getPassword())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/carts")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_목록_조회_요청(User user) {

        return RestAssured.given().log().all()
                .auth().preemptive().basic(user.getEmail(), user.getPassword())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/carts")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장바구니_삭제_요청(User user, Long cartId) {

        return RestAssured.given().log().all()
                .auth().preemptive().basic(user.getEmail(), user.getPassword())
                .when()
                .delete("/carts/{cartId}", cartId)
                .then().log().all()
                .extract();
    }
}
