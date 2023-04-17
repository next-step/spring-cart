package cart.api;

import cart.dao.ProductDao;
import cart.domain.Product;
import cart.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductAdminService {
    private final ProductDao productDao;

    public ProductAdminService(ProductDao adminProductDao) {
        this.productDao = adminProductDao;
    }

    public int createProduct(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        return productDao.insertProduct(product).getId();
    }

    public void updateProduct(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        productDao.updateProduct(product);
    }

    public void deleteProduct(Integer id) {
        productDao.deleteProdect(id);
    }

    public List<Product> selectAllProduct() {
        return productDao.selectProducts();
    }

    public Product selectOneProduct(int id) {
        return productDao.selectOneProduct(id);
    }


    @Override
    public String toString() {
        return "ProductAdminService{" +
                "ProductDao=" + productDao +
                '}';
    }
}
