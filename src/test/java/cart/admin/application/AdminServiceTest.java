package cart.admin.application;

import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import org.junit.jupiter.api.BeforeEach;
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
    void init(){
        jdbcTemplate.execute("delete from product_list");
    }

    @Test
    public void  createProduct() {
        int id = adminService.createProduct(new Product("productName", "productPath", 1000));

        assertThat(id).isNotNull();
    }

    @Test
    public void selectProductTest(){

        Product product = new Product("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        Product selectProduct = adminService.selectOneProduct(id);

        assertThat(selectProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(selectProduct.getId()).isEqualTo(id);
    }

    @Test
    public void updateProduct() {

        Product product = new Product("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        Product selectProduct = adminService.selectOneProduct(id);

        selectProduct.setName("test");

        adminService.updateProduct(selectProduct);

        selectProduct = adminService.selectOneProduct(id);

        assertThat(selectProduct.getName()).isNotEqualTo(product.getName());
        assertThat(selectProduct.getName()).isEqualTo("test");

    }

    @Test
    public void deleteProduct() {

        Product product = new Product("productName", "productPath", 1000);
        int id = adminService.createProduct(product);

        adminService.deleteProduct(id);

        Product selectProduct = adminService.selectOneProduct(id);
        assertThat(selectProduct).isNull();

    }

    @Test
    public void selectAllProduct () {

        adminService.createProduct(new Product("LUCAS", "imageUrl", 1000));
        adminService.createProduct(new Product("VINCENT", "imageUrl", 2000));
        adminService.createProduct(new Product("KAI", "imageUrl", 3000));

        int length = adminService.selectAllProduct().size();
        assertThat(length).isEqualTo(3);
    }


}
