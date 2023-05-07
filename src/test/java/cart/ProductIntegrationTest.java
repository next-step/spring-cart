package cart;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static cart.ProductTestStep.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductIntegrationTest extends AcceptanceTest {

    @Test
    @DisplayName("상품 생성 테스트.")
    public void 상품생성_테스트() {
        Map<String, String> params = new HashMap<>();
        params.put("name", "hero");
        params.put("image", "/users/test");
        params.put("price", "1000");

        ExtractableResponse<Response> response = 상품_생성요청(params);


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
