package cart.product.domain;

import java.util.Objects;

public class ProductImage {
    private final String value;

    public ProductImage(String value) {
        this.value = value;
        validateNullOrBlank(this.value);
    }

    private void validateNullOrBlank(String image) {
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("상품 이미지는 필수 값입니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImage that = (ProductImage) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
