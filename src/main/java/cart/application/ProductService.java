package cart.application;

import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.controller.dto.ProductsResponse;
import cart.domain.Product;
import cart.domain.ProductRepository;
import cart.domain.Products;
import cart.exception.ErrorType;
import cart.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsResponse getAllProducts() {
        productRepository.findAll();

        return null;
    }

}
