package cart.product.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductPrice {
    private final BigDecimal value;

    public ProductPrice(Long value) {
        this(new BigDecimal(value));
    }

    public ProductPrice(BigDecimal value) {
        this.value = value;
        validatePositive(this.value);
    }

    private void validatePositive(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("상품 가격은 0원 이상이어야 합니다.");
        }
    }

    public Long getLongValue() {
        return value.longValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
