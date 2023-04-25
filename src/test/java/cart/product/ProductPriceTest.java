package cart.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("상품 가격 테스트")
class ProductPriceTest {

    @DisplayName("상품 가격은 0원 이상이어야 한다.")
    @Test
    void productPriceCannotBeLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new ProductPrice(-1L));
    }

    @DisplayName("상품 가격은 동등성 비교가 가능하다.")
    @Test
    void productPriceIsEquatable() {
        var productPrice = new ProductPrice(1_000L);
        assertEquals(productPrice, new ProductPrice(1_000L));
    }
}