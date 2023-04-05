package cart.admin.application;

import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import cart.admin.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminService adminService;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("delete from product_list");
    }

    @DisplayName("상품 생성 테스트")
    @Test
    public void createProduct() {
        int id = adminService.createProduct(new ProductDto("productName", "productPath", 1000));

        assertThat(id).isNotNull();
    }

    @DisplayName("상품 조회 테스트")
    @Test
    public void selectProductTest() {

        ProductDto product = new ProductDto("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        Product selectProduct = adminService.selectOneProduct(id);

        assertThat(selectProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(selectProduct.getId()).isEqualTo(id);
    }

    @DisplayName("상품 업데이트 테스트")
    @Test
    public void updateProduct() {

        ProductDto product = new ProductDto("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        Product selectProduct = adminService.selectOneProduct(id);

        adminService.updateProduct(new ProductDto(selectProduct.getId(), "test", "url", 1000));

        selectProduct = adminService.selectOneProduct(id);

        assertThat(selectProduct.getName()).isNotEqualTo(product.getName());
        assertThat(selectProduct.getName()).isEqualTo("test");

    }

    @DisplayName("상품삭제 테스트")
    @Test
    public void deleteProduct() {

        ProductDto product = new ProductDto("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        adminService.deleteProduct(id);

        Product selectProduct = adminService.selectOneProduct(id);
        assertThat(selectProduct).isNull();

    }

    @DisplayName("상품전체 조회 서비스 테스트")
    @Test
    public void selectAllProduct() {

        adminService.createProduct(new ProductDto("LUCAS", "imageUrl", 1000));
        adminService.createProduct(new ProductDto("VINCENT", "imageUrl", 2000));
        adminService.createProduct(new ProductDto("KAI", "imageUrl", 3000));

        int length = adminService.selectAllProduct().size();
        assertThat(length).isEqualTo(3);
    }


}
