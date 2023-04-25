package cart.product;

import java.util.Objects;

public class ProductId {
    private final Long value;

    public ProductId(Long value) {
        this.value = value;
        validatePositive(this.value);
    }

    private void validatePositive(Long value) {
        if (value < 0) {
            throw new IllegalArgumentException("상품 ID는 0 이상이어야 합니다.");
        }
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
