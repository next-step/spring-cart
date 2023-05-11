package cart.product.domain.service;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import cart.product.domain.vo.ImagePath;
import cart.product.domain.vo.ProductName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductDto createProduct(String productName, int price, String imagePath) {
        return ProductDto.fromEntity(productRepository.insert(Product.builder()
                .name(new ProductName(productName))
                .price(price)
                .image(new ImagePath(imagePath))
                .build()));
    }

    public ProductDto getProduct(Long id) {
        return ProductDto.fromEntity(productRepository.findById(id));
    }

    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return ProductDto.fromEntity(
                productRepository.updateProduct(id,
                        Product.builder()
                                .name(new ProductName(productDto.getName()))
                                .price(productDto.getPrice())
                                .image(new ImagePath(productDto.getImage()))
                                .build()));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
