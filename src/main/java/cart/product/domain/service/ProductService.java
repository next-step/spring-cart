package cart.product.domain.service;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto createProduct(String productName, int price, String imagePath) {
        return ProductDto.fromEntity(productRepository.insert(Product.builder()
                .name(productName)
                .price(price)
                .image(imagePath)
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

    public void updateProduct(Long id, ProductDto productDto) {
        productRepository.updateProduct(id,
                Product.builder()
                        .name(productDto.getName())
                        .price(productDto.getPrice())
                        .image(productDto.getImage())
                        .build());
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
