package cart.product.application;

import cart.product.application.dto.ProductInformation;
import cart.product.domain.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductInformation> findAll() {
        return ProductInformation.from(productRepository.findAll());
    }
}
