package cart.repository;

import static org.assertj.core.api.Assertions.assertThat;

import cart.domain.Member;
import cart.domain.Product;
import cart.dto.ProductCreateDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductRepositoryTest {

  private ProductRepository repository;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  List<Object[]> products = new ArrayList<>();

  @BeforeEach
  void setUp() {
    repository = new ProductRepository(jdbcTemplate);
    jdbcTemplate.execute("DROP TABLE IF EXISTS product");
    jdbcTemplate.execute(
        "CREATE TABLE product (ID INT AUTO_INCREMENT PRIMARY KEY, NAME CHARACTER VARYING(50), IMAGE CHARACTER VARYING(300), PRICE DOUBLE PRECISION)");

    products = Arrays.asList(
        new Object[]{"치킨",
            "https://i.namu.wiki/i/XKWB62KmN4OJyHfKh-WIcwQvxQttQxCXvT8WdmLxshN-0T-6bjRgvZCiohOjAzFKWt-uF5jwN_GDxHO_4wo6tQjFDeIzamfw8ASkvEJkdxURmMUCZcdTUXd-BLDXg_vG_4Y4CMZmLj-mseoAjr7hIA.webp",
            10000.0},
        new Object[]{"샐러드",
            "https://i.namu.wiki/i/ZoB2vB9L_Do-LeBWyob8D0h2ibeQ0xBdqxA8KQjuD6RNtL9cBmavn92Jo5jLRS9uoD_-B5-ELKWE6d2NToOScgt4cKTeNIEXkGhu6dThUUgyoGKD-NJvzSjBYyWV7l7DjX1VwuBohjCffif4vMAWFw.webp",
            20000.0},
        new Object[]{"피자",
            "https://i.namu.wiki/i/HiqfW42Vm1Fblprs59MT3pFS0CSGlwBel5cem5ILozlV6Q9dCyATb9KzoBMiRd43S9eyJ4bfi8nPvCCfrSjtqNhoa7W_cHbM8YbuuNVAlsRfVRvmxqgEt6xMuvjmHAUjCvbeLOk7Ka6Vff3f8oHB8w.webp",
            13000.0}
    );

    jdbcTemplate.batchUpdate("INSERT INTO product (NAME, IMAGE, PRICE) VALUES (?,?,?)", products);
  }

  @DisplayName("전체 상품을 조회한다.")
  @Test
  void getAll() {
    List<Product> products = repository.getAll();
    assertThat(products).hasSize(3);
  }

  @DisplayName("상품의 정보를 수정한다.")
  @Test
  void update() {
    Product product = new Product(1L, "coffee", "test.jpg", new BigDecimal("10000"));
    repository.update(product);

    Product updatedProduct = repository.findById(product.getId());
    assertThat(product.getName()).isEqualTo(updatedProduct.getName());
  }

  @DisplayName("상품의 id로 상품을 조회한다.")
  @Test
  void findById() {
    Product product = repository.findById(1L);
    assertThat(product).isNotNull();
    assertThat(product.getName()).isEqualTo("치킨");
  }

  @DisplayName("상품의 id로 상품을 삭제한다.")
  @Test
  void deleteById() {
    repository.deleteById(1L);

    List<Product> products = repository.getAll();
    assertThat(products).hasSize(2);
  }

  @DisplayName("상품을 등록한다.")
  @Test
  void save() {
    ProductCreateDto createDto = new ProductCreateDto("kimbab", "kimbab.jpg", new BigDecimal(1000));
    repository.save(createDto.toEntity());

    List<Product> products = repository.getAll();
    assertThat(products).hasSize(4);
  }

  @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
  static
  class MemberServiceTest {

    private MemberRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
      repository = new MemberRepository(jdbcTemplate);
      jdbcTemplate.execute("DROP TABLE IF EXISTS member");
      jdbcTemplate.execute("create table member\n"
          + "(\n"
          + "    ID INT AUTO_INCREMENT PRIMARY KEY,\n"
          + "    EMAIL  CHARACTER VARYING(50),\n"
          + "    PASSWORD CHARACTER VARYING(50)\n"
          + ")");
    }

    @Test
    void findAll() {
      List<Member> members =repository.findAll();
      System.out.println(members.size());

      System.out.println(members==null);

      System.out.println(members.isEmpty());
    }

    @Test
    void findByEmail() {
    }

    @Test
    void authenticate() {
    }
  }
}
