package cart.service;

import cart.dao.ProductDao;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void createProduct(Product product) {
        productDao.insertProduct(product.getName(), product.getImageUrl(), product.getPrice());
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product.getId());
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public List<Product> showProducts(){
        List<Product> result = productDao.selectProduct();
        return result;
    }

}
