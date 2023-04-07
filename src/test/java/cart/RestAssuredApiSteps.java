package cart;


import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class RestAssuredApiSteps<T> {

    public ExtractableResponse<Response> getUrl(String url) {
        return getWhen()
                .get(url)
                .then()
                .extract();
    }

    public ExtractableResponse<Response> createUrlWithBody(String url, T bodyData) {

        return getWhen()
                .body(bodyData)
                .post(url)
                .then()
                .extract();
    }

    public ExtractableResponse<Response> updateUrlWithBody(String url, T bodyData) {

        return getWhen()
                .body(bodyData)
                .put(url)
                .then()
                .extract();
    }

    private static RequestSpecification getWhen() {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when();
    }

}
