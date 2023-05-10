package cart.member.controller;

import cart.fixture.AssuredTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cart.member.controller.MemberStep.유저_생성_API_요청;
import static cart.member.controller.MemberStep.유저_생성_응답_검증;

class MemberApiControllerTest extends AssuredTest {

    private static final String EMAIL = "mail";
    private static final String PASSWORD = "password";

    @DisplayName("유저 생성 요청, 응답 테스트")
    @Test
    void createProduct() {
        // when
        ExtractableResponse<Response> extractableResponse = 유저_생성_API_요청(EMAIL, PASSWORD);

        // then
        유저_생성_응답_검증(extractableResponse);
    }

}
