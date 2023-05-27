package cart.acceptance;

import cart.controller.dto.request.CartAddRequest;
import cart.domain.User;
import cart.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static cart.acceptance.CartSteps.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("장바구니 관련 기능 인수테스트")
class CartAcceptanceTest {

    private final CartAddRequest cartAddRequest = new CartAddRequest(1);
    private final CartAddRequest cartAddRequest2 = new CartAddRequest(2);
    private final CartAddRequest cartAddRequest3 = new CartAddRequest(3);

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("/cart 로 접근할 경우 장바구니 목록 조회에 성공한다.")
    void getProductsTest() {

        var response = 장바구니_목록_요청("/cart");

        assertThat(response.statusCode()).isEqualTo(OK.value());
    }

    @Test
    @DisplayName("/cart 와 다른 path로 접근할 경우 상품 목록 조회에 실패한다.")
    void getProductsExceptionTest() {

        var response = 장바구니_목록_요청("/peo");

        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }


    @Test
    @DisplayName("장바구니에 상품을 추가할 수 있다.")
    void addProduct() {

        var response = 장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(CREATED.value()),
                () -> assertThat(response.jsonPath().getLong("productId")).isEqualTo(cartAddRequest.getProductId())
        );
    }

    @Test
    @DisplayName("인증된 사용자가 아니라면 장바구니 상품 추가에 실패한다.")
    void addProductExceptionTest() {

        var response = 장바구니_상품_추가_요청(new User("jisoo@gmail.com", "aaasss1"), cartAddRequest);

        assertThat(response.statusCode()).isEqualTo(UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("장바구니에 상품을 추가할 시 상품이 존재하지 않는다면 추가에 실패한다.")
    void addProductExceptionTest2() {

        var response = 장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest3);

        assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
    }

    @Test
    @DisplayName("장바구니 목록을 조회할 수 있다.")
    void findAllTest() {

        장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest);
        장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest2);

        var response = 장바구니_목록_조회_요청(userRepository.findAll().get(0));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                () -> assertThat(response.jsonPath().getList("cartsResponse.id")).contains(cartAddRequest.getProductId(), cartAddRequest2.getProductId())
        );

    }

    @Test
    @DisplayName("장바구니 목록 조회 시 인증된 사용자가 아니라면 조회에 실패한다.")
    void findAllExceptionTest() {

        var response = 장바구니_목록_조회_요청(new User("jisoo@gmail.com", "aaafff1"));

        assertThat(response.statusCode()).isEqualTo(UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("장바구니에 추가된 상품을 삭제할 수 있다.")
    void deleteTest() {

        long cartId = 장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest).jsonPath().getLong("id");

        var response = 장바구니_삭제_요청(userRepository.findAll().get(0), cartId);

        assertThat(response.statusCode()).isEqualTo(NO_CONTENT.value());
    }

    @Test
    @DisplayName("장바구니 삭제 시 장바구니를 추가한 유저와 삭제하려는 유저가 다르려면 삭제에 실패한다.")
    void deleteExceptionTest() {

        long cartId = 장바구니_상품_추가_요청(userRepository.findAll().get(0), cartAddRequest).jsonPath().getLong("id");

        var response = 장바구니_삭제_요청(new User("jisoo@gmail.com", "aaafff1"), cartId);

        assertThat(response.statusCode()).isEqualTo(UNAUTHORIZED.value());
    }
}
