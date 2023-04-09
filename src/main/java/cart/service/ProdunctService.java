package cart.service;

import cart.dao.ProductDao;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdunctService {
    private ProductDao productDao;

    public ProdunctService(ProductDao productDao) {
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

    public List<Product> showProduct(){
        List<Product> result = productDao.selectProduct();
        return result;
    }

}
