package cart.product.domain.service;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
