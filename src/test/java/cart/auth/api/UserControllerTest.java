package cart.auth.api;

import cart.auth.domain.User;
import cart.RestAssuredApiSteps;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    RestAssuredApiSteps<User> productRestAssuredApiSteps;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        this.productRestAssuredApiSteps = new RestAssuredApiSteps<>();
    }

    @DisplayName("유저 셋팅정보 조회")
    @Test
    public void getAdminProducts() {
        var result = given()
                .when()
                .get("/users/settings")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


}
