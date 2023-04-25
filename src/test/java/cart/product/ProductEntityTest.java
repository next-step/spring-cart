package cart.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("상품 엔티티 테스트")
class ProductEntityTest {

    @DisplayName("상품 엔티티는 ID 를 통해서 동등을 비교한다.")
    @Test
    void productIsEquatable() {
        ProductId productId = new ProductId(1L);
        Product apple = new Product("사과", "사과 이미지", 1_000L);
        Product banana = new Product("바나나", "바나나 이미지", 2_000L);

        ProductEntity appleEntity = new ProductEntity(productId, apple);
        ProductEntity bananaEntity = new ProductEntity(productId, banana);

        assertEquals(appleEntity, bananaEntity);
    }
}