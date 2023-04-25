package cart.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("상품 이미지 테스트")
class ProductImageTest {

    @DisplayName("상품 이미지는 공백으로 이루어질 수 없다.")
    @Test
    void productImageCannotBeBlank() {
        assertThrows(IllegalArgumentException.class, () -> new ProductImage(" "));
    }

    @DisplayName("상품 이미지는 null이 될 수 없다.")
    @Test
    void productImageCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProductImage(null));
    }

    @DisplayName("상품 이미지는 동등성 비교가 가능하다.")
    @Test
    void productImageIsEquatable() {
        var productImage = new ProductImage("상품 이미지");
        assertEquals(productImage, new ProductImage("상품 이미지"));
    }
}