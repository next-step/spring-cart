package cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cart.domain.Product;
import cart.dto.ProductCreateDto;
import cart.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    productService = new ProductService(productRepository);
  }

  @DisplayName("전체 상품을 조회한다")
  @Test
  void getAll() {
    // Given
    List<Product> expectedProducts = new ArrayList<>();
    expectedProducts.add(new Product(1L, "Product 1", "image1.jpg", new BigDecimal(1000)));
    expectedProducts.add(new Product(2L, "Product 2", "image2.jpg", new BigDecimal(2000)));
    when(productRepository.getAll()).thenReturn(expectedProducts);

    // When
    List<Product> actualProducts = productService.getAll();

    // Then
    assertEquals(expectedProducts, actualProducts);
    verify(productRepository).getAll();
  }

  @DisplayName("상품 아이디로 상품을 조회한다.")
  @Test
  void findById() {
    // Given
    Product product = new Product(1L, "Product 1", "image1.jpg", new BigDecimal(1000));
    when(productRepository.findById(product.getId())).thenReturn(product);

    // When
    Product actualProduct = productService.findById(product.getId());

    // Then
    assertEquals(product.getName(), actualProduct.getName());
  }

  @DisplayName("상품을 추가한다.")
  @Test
  void addProduct() {
    // Given
    ProductCreateDto createDto = new ProductCreateDto("Product 1", "image1.jpg",
        new BigDecimal(1000));

    // When
    productService.addProduct(createDto);

    // Then
    verify(productRepository).save(any(Product.class));
  }

  @DisplayName("상품의 아이디로 상품을 삭제한다.")
  @Test
  void delete() {
    // Given
    List<Product> expectedProducts = new ArrayList<>();
    Product product1 = new Product(1L, "Product 1", "image1.jpg", new BigDecimal(1000));
    Product product2 = new Product(2L, "Product 2", "image2.jpg", new BigDecimal(2000));
    expectedProducts.add(product1);
    expectedProducts.add(product2);

    // When
    productService.delete(product1.getId());
    expectedProducts.remove(product1);

    // Then
    assertEquals(1, expectedProducts.size());
  }

}
