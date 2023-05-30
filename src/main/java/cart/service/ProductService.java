package cart.service;

import cart.domain.Product;
import cart.dto.ProductCreateDto;
import cart.dto.ProductUpdateDto;
import cart.exception.NotFoundEntityException;
import cart.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional(readOnly = true)
  public List<Product> getAll() {
    return productRepository.getAll();
  }

  public void updateProduct(ProductUpdateDto updateDto, Long productId) {
    Product product = this.findById(productId);
    productRepository.update(updateDto.toEntity(product));
  }

  @Transactional(readOnly = true)
  public Product findById(Long id) {
    Product product = productRepository.findById(id);
    if (product == null) {
      throw new NotFoundEntityException("Product");
    }
    return product;
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  public void addProduct(ProductCreateDto createDto) {
    productRepository.save(createDto.toEntity());
  }
}
