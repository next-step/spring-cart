package cart.product.domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class PriceTest {

    @Test
    @DisplayName("입력값 가격이 0 이하일 경우 에러를 던진다")
    void fail_when_input_value_under_zero() {
        // given
        int price = -1;

        Assertions.assertThatThrownBy(() -> {
                    new Price(price);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 0이하일 수 없습니다");
    }

}
