package cart.product.domain;

import cart.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Objects;

import static cart.exception.ErrorType.INVALID_MONEY;

public class Money {

    private static final int MINIMUM_VALUE = 0;

    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money of(long value) {
        if (value < MINIMUM_VALUE) {
            throw new ServiceException(INVALID_MONEY);
        }

        return new Money(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public long getLongValue() {
        return value.longValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
