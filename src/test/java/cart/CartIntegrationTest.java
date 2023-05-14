package cart;

import static cart.ProductIntegrationTest.createProduct;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import cart.domain.Member;
import cart.domain.Members;
import cart.domain.Product;
import cart.dto.CartRequest;
import cart.dto.CartResponse;
import cart.dto.ProductRequest;
import io.restassured.RestAssured;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartIntegrationTest {

    private final Member member = new Member("email@email.com", "1234");
    private final String authToken = new String(
            Base64.encodeBase64((member.getEmail() + ":" + member.getPassword()).getBytes())
    );

    @LocalServerPort
    private int port;

    public CartIntegrationTest(@Autowired Members members) {
        members.add(member);
    }

    @DisplayName("장바구니에 아이템을 추가한다.")
    @DirtiesContext
    @Test
    public void addCart() {
        var productId = createProduct(new ProductRequest("bbq", "images/sample.jpeg", 20000)).getId();

        var cartRequest = new CartRequest(productId);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + authToken)
                .body(cartRequest)
                .when()
                .post("/member/cart")
                .then()
                .extract();

        var created = result.body().as(CartResponse.class);
        assertAll(
                () -> assertThat(created.getId()).isNotNull(),
                () -> assertThat(created.getMemberId()).isEqualTo(member.getId()),
                () -> assertThat(created.getProduct().getId()).isEqualTo(productId)
        );
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("사용자 장바구니 목록을 조회한다.")
    @DirtiesContext
    @Test
    public void getCarts() {
        var cartsSize = 4;
        for (int i = 0; i < cartsSize; i++) {
            createCart(member, new Product("name" + i, "images/sample.jpeg", 20000));
        }
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + authToken)
                .when()
                .get("/member/cart")
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.as(List.class).size()).isEqualTo(cartsSize);
    }

    @DisplayName("장바구니에서 아이템을 제거한다.")
    @Test
    public void deleteCart() {
        var savedCart = createCart(member, new Product("bbq", "images/sample.jpeg", 20000));
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/member/cart/" + savedCart.getId())
                .then()
                .extract();
        assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static CartResponse createCart(Member member, Product product) {
        var authToken = new String(Base64.encodeBase64((member.getEmail() + ":" + member.getPassword()).getBytes()));
        var productId = createProduct(
                new ProductRequest(product.getName(), product.getImageUrl(), product.getPrice())).getId();

        var cartRequest = new CartRequest(productId);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Basic " + authToken)
                .body(cartRequest)
                .when()
                .post("/member/cart")
                .then()
                .extract();
        return result.body().as(CartResponse.class);
    }
}
