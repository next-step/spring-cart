package cart.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("상품 ID 테스트")
class ProductIdTest {

    @DisplayName("상품 ID는 0보다 작을 수 없다.")
    @Test
    void productIdCannotBeLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new ProductId(-1L));
    }

    @DisplayName("상품 ID는 동등성 비교가 가능하다.")
    @Test
    void productIdIsEquatable() {
        var productId = new ProductId(1L);
        assertEquals(productId, new ProductId(1L));
    }
}