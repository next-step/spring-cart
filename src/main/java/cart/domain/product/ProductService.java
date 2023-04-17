package cart.domain.product;

import cart.domain.product.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> productList() {
        return productRepository.findAll();
    }

    public Long save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public void update(Long id, ProductModel productModel) {
        productRepository.update(id, productModel);
    }
}
