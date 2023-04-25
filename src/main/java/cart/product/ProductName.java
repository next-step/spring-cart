package cart.product;

import java.util.Objects;

public class ProductName {
    private final String value;

    public ProductName(String value) {
        this.value = value;
        validateNullOrBlank(this.value);
    }

    private void validateNullOrBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품명은 필수 값입니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
