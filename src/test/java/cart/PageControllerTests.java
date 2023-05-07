package cart;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class PageControllerTests extends AcceptanceTest{
    @DisplayName("상품목록 조회")
    @Test
    public void getProducts() {
        ExtractableResponse<Response> response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/")
                .then().log().all()
                .extract();


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
