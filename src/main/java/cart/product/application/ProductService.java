package cart.product.application;

import cart.product.application.dto.ProductCreate;
import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductUpdate;
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

    public ProductInformation create(ProductCreate productCreate) {
        return ProductInformation.from(productRepository.save(productCreate.toProduct()));
    }

    public ProductInformation update(ProductUpdate productUpdate) {
        return ProductInformation.from(productRepository.update(productUpdate.toProduct()));
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
