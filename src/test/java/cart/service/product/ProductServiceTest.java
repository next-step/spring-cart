package cart.service.product;

import cart.domain.product.Product;
import cart.infrastructure.ProductDao;
import cart.web.dto.ProductResponseDto;
import cart.web.dto.ProductSaveRequestDto;
import cart.web.dto.ProductUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductService productService;

    @Test
    void findAll() {
        // given
        insertProduct("상품A", "image.com/imageA", 10000);
        insertProduct("상품B", "image.com/imageB", 20000);

        // when
        List<ProductResponseDto> productResponseDtos = productService.findAll();

        // then
        assertThat(productResponseDtos).flatExtracting(ProductResponseDto::getName).containsExactly("상품A", "상품B");
        assertThat(productResponseDtos).flatExtracting(ProductResponseDto::getImageUrl)
                .containsExactly("image.com/imageA", "image.com/imageB");
        assertThat(productResponseDtos).flatExtracting(ProductResponseDto::getPrice).containsExactly(10000, 20000);
    }

    @Test
    void save() {
        // given
        ProductSaveRequestDto requestDto = new ProductSaveRequestDto("상품A", "image.com/imageA", 10000);

        // when
        Long savedProductId = productService.save(requestDto);

        // then
        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(savedProductId).get());
        assertThat(foundProduct.getName()).isEqualTo(requestDto.getName());
        assertThat(foundProduct.getImageUrl()).isEqualTo(requestDto.getImageUrl());
        assertThat(foundProduct.getPrice()).isEqualTo(requestDto.getPrice());
    }

    @Test
    void update() {
        // given
        Long insertedProductId = insertProduct("상품A", "image.com/imageA", 10000).getId();
        ProductUpdateRequestDto requestDto = new ProductUpdateRequestDto("상품B", "image.com/imageB", 20000);

        // when
        Long updatedProductId = productService.update(insertedProductId, requestDto);

        // then
        Product foundProduct = assertDoesNotThrow(() -> productDao.findById(updatedProductId).get());
        assertThat(foundProduct.getName()).isEqualTo(requestDto.getName());
        assertThat(foundProduct.getImageUrl()).isEqualTo(requestDto.getImageUrl());
        assertThat(foundProduct.getPrice()).isEqualTo(requestDto.getPrice());
    }

    @Test
    void delete() {
        // given
        Long insertedProductId = insertProduct("상품A", "image.com/imageA", 10000).getId();

        // when
        Long deletedProductId = productService.delete(insertedProductId);

        // then
        assertThatThrownBy(() -> productDao.findById(deletedProductId).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Product insertProduct(String name, String imageUrl, int price) {
        Product givenProduct = Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();

        return productDao.insert(givenProduct);
    }
}
