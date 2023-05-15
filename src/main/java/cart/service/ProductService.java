package cart.service;

import cart.repository.ProductRepository;
import cart.domain.Product;
import cart.dto.ProductCreateDto;
import cart.dto.ProductUpdateDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAll() {
    return productRepository.getAll();
  }

  public void updateProduct(ProductUpdateDto updateDto) {
    productRepository.update(updateDto.toEntity());
  }

  public Product findById(Long id) {
    return productRepository.findById(id);
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  public void addProduct(ProductCreateDto createDto) {
    productRepository.save(createDto.toEntity());
  }
}
