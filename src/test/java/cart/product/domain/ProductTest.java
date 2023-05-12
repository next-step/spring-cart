package cart.product.domain;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import cart.product.domain.Money;
import cart.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    private static final String NAME = "테스트 상품";
    private static final String IMAGE = "test.png";
    private static final Money PRICE = Money.of(1000L);

    @DisplayName("상품 이름을 지정하지 않으면 예외를 발생시킨다.")
    @Test
    void validateName() {
        // then
        assertThatThrownBy(() ->
                Product.builder()
                        .image(IMAGE)
                        .price(PRICE)
                        .build())
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.INVALID_PRODUCT_NAME.getMessage());
    }

    @DisplayName("상품 이미지를 지정하지 않으면 예외를 발생시킨다.")
    @Test
    void validateImage() {
        // then
        assertThatThrownBy(() ->
                Product.builder()
                        .name(NAME)
                        .price(PRICE)
                        .build())
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.INVALID_PRODUCT_IMAGE.getMessage());
    }

    @DisplayName("상품 가격을 지정하지 않으면 예외를 발생시킨다.")
    @Test
    void validatePrice() {
        // then
        assertThatThrownBy(() ->
                Product.builder()
                        .name(NAME)
                        .image(IMAGE)
                        .build())
                .isInstanceOf(ServiceException.class)
                .hasMessage(ErrorType.INVALID_PRODUCT_PRICE.getMessage());
    }

    @DisplayName("상품의 정보를 업데이트할 수 있다.")
    @Test
    void update() {
        // given
        Product product = Product.builder()
                .name(NAME)
                .image(IMAGE)
                .price(PRICE)
                .build();
        String name = "테스트 상품 수정";
        String image = "test2.png";
        long price = 2000L;

        // when
        product.update(name, image, price);

        // then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getImage()).isEqualTo(image);
        assertThat(product.getPrice()).isEqualTo(price);
    }

}
