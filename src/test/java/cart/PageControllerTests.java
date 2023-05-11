package cart;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static cart.ProductTestStep.*;
import static org.assertj.core.api.Assertions.assertThat;

class PageControllerTests extends AcceptanceTest{
    @DisplayName("상품목록 조회")
    @Test
    public void getProducts() {
        ExtractableResponse<Response> response = 상품_전체목록_조회();


        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
