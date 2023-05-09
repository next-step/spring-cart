package cart.service.product;

import cart.domain.product.Product;
import cart.infrastructure.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDao productDao;

    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

}
