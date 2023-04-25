package cart.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("상품 테스트")
class ProductTest {

    @DisplayName("상품은 ID 를 통해서 동등을 비교한다.")
    @Test
    void productIsEquatable() {
        Product apple = new Product(1L, "사과", "사과 이미지", 1_000L);
        Product banana = new Product(1L, "바나나", "바나나 이미지", 2_000L);
        assertEquals(apple, banana);
    }
}