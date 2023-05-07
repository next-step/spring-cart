package cart;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static cart.ProductTestStep.상품_생성요청;
import static cart.ProductTestStep.상품_수정_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProductIntegrationTest extends AcceptanceTest {

    @Test
    @DisplayName("상품 생성 테스트.")
    public void 상품생성_테스트() {
        String name = "tori";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("image", "/users/test");
        params.put("price", "1000");

        ExtractableResponse<Response> response = 상품_생성요청(params);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(name)
        );
    }

    @Test
    @DisplayName("상품 수정 테스트.")
    public void 상품수정_테스트() {
        String name = "update";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);


        ExtractableResponse<Response> response = 상품_수정_요청(params, 2);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(name)
        );
    }

    @Test
    @DisplayName("상품 수정 실패 테스트 - 해당 id에 해당하는 상품이 없습니다.")
    public void 상품수정_실패_테스트() {
        String name = "update";
        Map<String, String> params = new HashMap<>();
        params.put("name", name);


        ExtractableResponse<Response> response = 상품_수정_요청(params, 3);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
    }

}
