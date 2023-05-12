package cart.product.domain;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import cart.product.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("Money는 0이상의 값으로 생성 가능하다.")
    @ParameterizedTest(name = "Money는 {0} 값으로 생성 가능하다.")
    @ValueSource(ints = {0, 1, 10, 50, 1000})
    void of(int value) {
        // given
        BigDecimal expected = BigDecimal.valueOf(value);

        // when
        Money money = Money.of(value);

        // then
        assertThat(money.getValue()).isEqualTo(expected);
    }

    @DisplayName("Money는 0미만의 값으로 생성 불가능하다.")
    @ParameterizedTest(name = "Money는 {0} 값으로 생성 불가능하다.")
    @ValueSource(ints = {-1000, -50, -10, -1})
    void of_fail(int value) {
        // then
        assertThatThrownBy(() -> Money.of(value))
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.INVALID_MONEY.getMessage());
    }

    @DisplayName("동등성 비교를 할 수 있다.")
    @Test
    void equals() {
        // given
        long value = 1000L;
        Money money1 = Money.of(value);
        Money money2 = Money.of(value);

        // when
        boolean equals = money1.equals(money2);

        // then
        assertThat(equals).isTrue();
    }
}
