package cart.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import cart.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:data.sql"})
@SpringBootTest
class ProductRepositoryTest {

  private ProductRepository repository;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private DataSource dataSource;

  @BeforeEach
  void setUp() {
    repository = new ProductRepository(jdbcTemplate, dataSource);
  }

  @DisplayName("상품을 추가한다.")
  @Test
  void save() {
    Product insertedProduct = insertProduct("상품A", "image.jpg", new BigDecimal(10000));

    assertThat(insertedProduct.getId()).isNotNull();
  }

  @DisplayName("전체 상품을 조회한다.")
  @Test
  void getAll() {
    Product insertedProduct1 = insertProduct("상품A", "image.jpg", new BigDecimal(10000));
    Product insertedProduct2 = insertProduct("상품B", "image.jpg", new BigDecimal(10000));
    Product insertedProduct3 = insertProduct("상품C", "image.jpg", new BigDecimal(10000));

    List<Product> products = repository.getAll();

    assertThat(products).hasSize(3);
  }

  @DisplayName("상품의 정보를 수정한다.")
  @Test
  void update() {
    Product insertedProduct = insertProduct("상품A", "image.com/imageA", new BigDecimal(1000));
    Product updateProduct = Product.builder()
        .id(insertedProduct.getId())
        .name("상품B")
        .image("image.jpg")
        .price(new BigDecimal(20000))
        .build();

    Long updatedProductId = repository.update(updateProduct);

    assertThat(insertedProduct.getId()).isEqualTo(updatedProductId);
    Product foundProduct = assertDoesNotThrow(() -> repository.findById(updatedProductId));
    assertThat(foundProduct.getName()).isEqualTo(updateProduct.getName());
    assertThat(foundProduct.getImage()).isEqualTo(updateProduct.getImage());
    assertThat(foundProduct.getPrice().intValue()).isEqualTo(updateProduct.getPrice().intValue());

  }

  @DisplayName("상품의 id로 상품을 조회한다.")
  @Test
  void findById() {
    Product insertedProduct = insertProduct("상품A", "image.com/imageA", new BigDecimal(1000));
    Product foundProduct = repository.findById(insertedProduct.getId());

    assertThat(foundProduct).isNotNull();
    assertThat(insertedProduct.getName()).isEqualTo(foundProduct.getName());
  }

  private Product insertProduct(String name, String imageUrl, BigDecimal price) {
    Product givenProduct = Product.builder()
        .name(name)
        .image(imageUrl)
        .price(price)
        .build();

    return repository.save(givenProduct);
  }
}
