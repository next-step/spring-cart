package cart.unit;

import cart.controller.dto.request.CartAddRequest;
import cart.controller.dto.request.ProductRequest;
import cart.controller.dto.response.CartResponse;
import cart.controller.dto.response.CartsResponse;
import cart.domain.Product;
import cart.exception.JwpCartApplicationException;
import cart.repository.CartRepository;
import cart.repository.ProductRepository;
import cart.repository.UserRepository;
import cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static cart.unit.ProductServiceTest.createRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @DisplayName("장바구니 등록 테스트")
    @Test
    void addTest() {
        // given
        Product product = productRepository.save(createRequest);

        // when
        CartAddRequest cartAddRequest = new CartAddRequest(product.getId().intValue());
        CartResponse cartResponse = cartService.add(userRepository.findAll().get(0), cartAddRequest);

        // then
        assertAll(
                () -> assertThat(cartRepository.count()).isEqualTo(1),
                () -> assertThat(cartResponse.getProductId()).isEqualTo(cartAddRequest.getProductId())
        );
    }

    @DisplayName("장바구니 등록 예외 테스트 - 상품이 존재하지 않을 경우")
    @Test
    void addExceptionTest() {

        assertThatThrownBy(() -> cartService.add(userRepository.findAll().get(0), new CartAddRequest(1)))
                .isInstanceOf(JwpCartApplicationException.class);
    }


    @DisplayName("장바구니 목록 조회 테스트")
    @Test
    void findAllTest() {

        // given
        Product product1 = productRepository.save(createRequest);
        Product product2 = productRepository.save(new ProductRequest("찜닭", "//", 5000));
        CartAddRequest cartAddRequest1 = new CartAddRequest(product1.getId().intValue());
        CartAddRequest cartAddRequest2 = new CartAddRequest(product2.getId().intValue());
        cartService.add(userRepository.findAll().get(0), cartAddRequest1);
        cartService.add(userRepository.findAll().get(0), cartAddRequest2);

        // when
        CartsResponse response = cartService.findAll(userRepository.findAll().get(0));

        // then
        assertAll(
                () -> assertThat(cartRepository.count()).isEqualTo(2),
                () -> assertThat(response.getCartsResponse().get(0).getProductId()).isEqualTo(cartAddRequest1.getProductId()),
                () -> assertThat(response.getCartsResponse().get(1).getProductId()).isEqualTo(cartAddRequest2.getProductId())
        );
    }

    @DisplayName("장바구니 삭제 테스트")
    @Test
    void deleteTest() {

        // given
        Product product1 = productRepository.save(createRequest);
        CartResponse cartResponse = cartService.add(userRepository.findAll().get(0), new CartAddRequest(product1.getId().intValue()));

        // when
        cartService.delete(userRepository.findAll().get(0), cartResponse.getId());

        // then
        assertThat(cartRepository.count()).isZero();
    }

    @DisplayName("장바구니 삭제 예외 테스트 - 추가된 장바구니가 존재하지 않을 경우")
    @Test
    void deleteExceptionTest() {
        assertThatThrownBy(() -> cartService.delete(userRepository.findAll().get(0), 1L))
                .isInstanceOf(JwpCartApplicationException.class);
    }
}
