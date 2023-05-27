package cart.service;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import cart.controller.dto.response.ProductResponse;
import cart.controller.dto.response.ProductsResponse;
import cart.domain.Product;
import cart.exception.JwpCartApplicationException;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import static cart.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductsResponse findAll() {
        List<Product> products = productRepository.findAll();
        return ProductsResponse.of(products.stream().map(ProductResponse::of).collect(Collectors.toList()));
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()
                -> new JwpCartApplicationException(PRODUCT_NOT_FOUND));
    }

    public ProductResponse createProductResponse(Product product) {
        return ProductResponse.of(product);
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest);
        return ProductResponse.of(product);
    }

    public void edit(Long productId, ProductEditRequest productEditRequest) {
        Product product = findById(productId);
        Product editProduct = product.edit(productEditRequest);
        productRepository.edit(productId, editProduct);
    }

    public void delete(Long productId) {
        findById(productId);
        productRepository.delete(productId);
    }
}
