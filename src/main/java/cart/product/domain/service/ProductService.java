package cart.product.domain.service;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import cart.product.web.dto.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(String productName, int price, String imagePath) {
        productRepository.insert(Product.builder()
                .name(productName)
                .price(price)
                .image(imagePath)
                .build());
    }

    public ProductInfo getProduct(Long id) {
        return ProductInfo.fromEntity(productRepository.findById(id));
    }

    public List<ProductInfo> getAllProduct() {
        return productRepository.findAll().stream()
                .map(ProductInfo::fromEntity)
                .collect(Collectors.toList());
    }
}
