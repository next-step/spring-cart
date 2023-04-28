package cart.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("상품 이름 테스트")
class ProductNameTest {

    @DisplayName("상품 이름은 공백으로 이루어질 수 없다.")
    @Test
    void productNameCannotBeBlank() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(" "));
    }

    @DisplayName("상품 이름은 null이 될 수 없다.")
    @Test
    void productNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(null));
    }

    @DisplayName("상품 이름은 동등성 비교가 가능하다.")
    @Test
    void productNameIsEquatable() {
        var productName = new ProductName("상품 이름");
        assertEquals(productName, new ProductName("상품 이름"));
    }
}