package cart.data;

import cart.data.entity.CartProduct;
import cart.presentation.dto.RequestProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductJdbcRepositoryTest {

    @DisplayName("상품생성 테스트")
    @Test
    void addProductTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        CartProduct cartProduct =
                new CartProduct(1L, "productName", 100, "test");
        repository.addProduct(cartProduct);
        assertThat(repository.getProducts().size()).isEqualTo(4);
    }

    @DisplayName("상품목록 조회 테스트")
    @Test
    void getProductsTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        assertThat(repository.getProducts().size()).isEqualTo(3);
    }

    @DisplayName("상품 단건 조회 테스트")
    @Test
    void getProductByIdSuccessTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        assertThat(repository.getProductById(1)).isNotEmpty();
    }

    @DisplayName("상품 단건 조회 실패 테스트")
    @Test
    void getProductByIdFailTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        assertThat(repository.getProductById(0)).isEmpty();
    }

    @DisplayName("수정 실패 테스트")
    @Test
    void updateFailTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        CartProduct cartProduct = repository.getProductById(1).get();
        RequestProduct requestProduct = new RequestProduct();
        cartProduct.modifyProduct(requestProduct);
        assertThrows(DataIntegrityViolationException.class, () -> repository.updateProduct(cartProduct));
    }

    @DisplayName("상품삭제 성공 테스트")
    @Test
    void removeSuccessTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        assertThat(repository.removeProduct(1)).isEqualTo(1);
    }

    @DisplayName("상품삭제 실패 테스트")
    @Test
    void removeFailTest() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test-data.sql")
                .build();
        ProductJdbcRepository repository = new ProductJdbcRepository(dataSource);
        assertThat(repository.removeProduct(0)).isEqualTo(0);
    }

}