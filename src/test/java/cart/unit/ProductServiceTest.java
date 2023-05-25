package cart.unit;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import cart.controller.dto.response.ProductResponse;
import cart.domain.Product;
import cart.exception.JwpCartApplicationException;
import cart.repository.ProductRepository;
import cart.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    private final ProductRequest createRequest = new ProductRequest("아이스크림", "https://shopping-phinf.pstatic.net/main_3859196/38591967419.20230313011518.jpg?type=f640", 2000);
    private final ProductEditRequest editRequest = new ProductEditRequest("햄버거", "https://www.mcdonalds.co.kr/upload/product/pcList/1683098039703.png", 10000);

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void saveProductTest() {

        // when
        ProductResponse productResponse = productService.save(createRequest);

        // then
        var product = productRepository.findById(productResponse.getId()).get();
        assertAll(
                () -> assertThat(product.getName()).isEqualTo(createRequest.getName()),
                () -> assertThat(product.getImage()).isEqualTo(createRequest.getImage()),
                () -> assertThat(product.getPrice()).isEqualTo(createRequest.getPrice())
                );
    }

    @Test
    @DisplayName("상품 단건 조회 테스트")
    void getProductTest() {

        // given
        var productResponse = productService.save(createRequest);

        // when
        productService.findById(productResponse.getId());

        // then
        var product = productRepository.findById(productResponse.getId()).get();
        assertAll(
                () -> assertThat(product.getName()).isEqualTo(createRequest.getName()),
                () -> assertThat(product.getImage()).isEqualTo(createRequest.getImage()),
                () -> assertThat(product.getPrice()).isEqualTo(createRequest.getPrice())
        );
    }

    @Test
    @DisplayName("상품 목록 조회 테스트")
    void getProductsTest() {

        // given
        productService.save(createRequest);
        ProductRequest createRequest2 = new ProductRequest("비빔밥", "https://i.namu.wiki/i/QYZceBXJdAtbHe_PWPM_qnRrKVbqnk8CVv27A7sQHJ9khnV9UheFm5qN0b2z-5FuuEOlskOGlsZeDrtTn8rikfElEY3EFk5XW9QhEGpRE3nmlPFTPNjdu6jaWvXEJeNQSoaBZtMYPqzDzX9obsQEsg.jpg", 8000);
        productService.save(createRequest2);

        // when
        List<Product> products = productService.findAll();

        // then
        assertAll(
                () -> assertThat(products.get(0).getName()).isEqualTo(createRequest.getName()),
                () -> assertThat(products.get(0).getImage()).isEqualTo(createRequest.getImage()),
                () -> assertThat(products.get(0).getPrice()).isEqualTo(createRequest.getPrice()),
                () -> assertThat(products.get(1).getName()).isEqualTo(createRequest2.getName()),
                () -> assertThat(products.get(1).getImage()).isEqualTo(createRequest2.getImage()),
                () -> assertThat(products.get(1).getPrice()).isEqualTo(createRequest2.getPrice())
        );
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void editProductTest() {

        // given
        var productResponse = productService.save(createRequest);

        // when
        productService.edit(productResponse.getId(), editRequest);

        // then
        var product = productRepository.findById(productResponse.getId()).get();
        assertAll(
                () -> assertThat(product.getName()).isEqualTo(editRequest.getName()),
                () -> assertThat(product.getImage()).isEqualTo(editRequest.getImage()),
                () -> assertThat(product.getPrice()).isEqualTo(editRequest.getPrice())
        );
    }

    @Test
    @DisplayName("상품 수정 예외 테스트 - 상품이 존재하지 않을 경우")
    void editProductExceptionTest() {

        // when & then
        Assertions.assertThatThrownBy(() -> productService.edit(1L, editRequest))
                .isInstanceOf(JwpCartApplicationException.class);
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProductTest() {

        // given
        ProductResponse productResponse = productService.save(createRequest);

        // when
        productService.delete(productResponse.getId());

        // then
        assertThat(productRepository.count()).isZero();
    }

    @Test
    @DisplayName("상품 삭제 예외테스트 - 상품이 존재하지 않을 경우")
    void deleteProductExceptionTest() {

        // when & then
        Assertions.assertThatThrownBy(() -> productService.delete(1L))
                .isInstanceOf(JwpCartApplicationException.class);
    }
}
