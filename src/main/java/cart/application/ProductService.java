package cart.application;

import cart.controller.dto.ProductResponse;
import cart.domain.Products;
import cart.infra.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author minsukim on 2023/05/07
 * @project jwp-cart
 * @description
 */
@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {this.productDao = productDao;}

    public List<ProductResponse> getAllProductList(){
        Products products = productDao.findAllProducts();

        return products.CreateProductResponseList();
    }
}
