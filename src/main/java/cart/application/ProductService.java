package cart.application;

import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.domain.Product;
import cart.domain.Products;
import cart.infra.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author minsukim on 2023/05/07
 * @project jwp-cart
 * @description
 */
@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productRequest.productOf();
        productDao.create(product);
        return ProductResponse.of(product);
    }
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest, int id) {
        Product product = getProductById(id);

        product.updateProduct(productRequest.getName(), productRequest.getImage(), productRequest.getPrice());
        productDao.update(product, id);
        return ProductResponse.of(product);
    }

    public List<ProductResponse> getAllProductList() {
        Products products = productDao.findAllProducts();

        return products.CreateProductResponseList();
    }

    public Product getProductById(int id) {
        return productDao.findById(id);
    }
}
