package cart.service.cart;

import cart.domain.cart.Cart;
import cart.infrastructure.dao.CartDao;
import cart.infrastructure.security.AccessDeniedException;
import cart.web.cart.dto.CartAddRequestDto;
import cart.web.cart.dto.CartResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static cart.service.user.UserServiceTest.USER_1;
import static cart.service.user.UserServiceTest.USER_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@Sql(scripts = "classpath:cartSampleData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartDao cartDao;

    @Test
    void 주어진_유저가_담은_모든_장바구니_아이템을_조회한다() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(1L));
        cartService.add(USER_2, new CartAddRequestDto(1L));
        cartService.add(USER_1, new CartAddRequestDto(2L));

        // when
        List<CartResponseDto> cartResponseDtos = cartService.findAll(USER_1);

        // then
        assertThat(cartResponseDtos).flatExtracting(CartResponseDto::getProductId)
                .containsExactly(1L, 2L);
        assertThat(cartResponseDtos).flatExtracting(CartResponseDto::getName)
                .containsExactly("상품A", "상품B");
        assertThat(cartResponseDtos).flatExtracting(CartResponseDto::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(cartResponseDtos).flatExtracting(CartResponseDto::getPrice)
                .containsExactly(10000, 20000);
    }

    @Test
    void 정상_주어진_유저의_장바구니에_아이템을_추가한다() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(3L);

        // when
        Long addedCartId = cartService.add(USER_1, requestDto);

        // then
        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(addedCartId).get());
        assertThat(foundCart.getUserId()).isEqualTo(USER_1.getId());
        assertThat(foundCart.getProductId()).isEqualTo(requestDto.getProductId());
    }

    @Test
    void 비정상_주어진_유저의_장바구니에_아이템을_추가한다_상품이_존재하지_않는_경우() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(4L);

        // when, then
        assertThatThrownBy(() -> cartService.add(USER_1, requestDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정상_주어진_유저의_장바구니에서_아이템을_삭제한다() {
        // given
        cartService.add(USER_1, new CartAddRequestDto(1L));

        // when
        Long removedCartId = cartService.remove(USER_1, 1L);

        // then
        assertThat(cartDao.findById(removedCartId)).isEmpty();
    }

    @Test
    void 비정상_주어진_유저의_장바구니에서_아이템을_삭제한다_장바구니_아이템이_존재하지_않는_경우() {
        assertThatThrownBy(() -> cartService.remove(USER_1, 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상_주어진_유저의_장바구니에서_아이템을_삭제한다_다른_유저의_아이템인_경우() {
        // given
        cartService.add(USER_2, new CartAddRequestDto(1L));

        // when, then
        assertThatThrownBy(() -> cartService.remove(USER_1, 1L))
                .isInstanceOf(AccessDeniedException.class);
    }
}
