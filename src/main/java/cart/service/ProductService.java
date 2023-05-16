package cart.service;

import cart.controller.dto.ProductEditRequest;
import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.domain.Product;
import cart.exception.JwpCartApplicationException;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static cart.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductResponse findById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new JwpCartApplicationException(PRODUCT_NOT_FOUND));
        return ProductResponse.of(product);
    }

    public ProductResponse save(ProductRequest pr) {
        Product product = productRepository.save(Product.of(pr.getName(), pr.getImage(), pr.getPrice()));
        return ProductResponse.of(product);
    }

    public void edit(Long productId, ProductEditRequest productEditRequest) {
        findById(productId);
        productRepository.edit(productId, productEditRequest);
    }

    public void delete(Long productId) {
        findById(productId);
        productRepository.delete(productId);
    }
}
