package cart;

import cart.dao.ProductDao;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ProductAdminService {
    private final ProductDao productDao;

    public ProductAdminService(ProductDao adminProductDao) {
        this.productDao = adminProductDao;
    }


    public List<Product> selectAll() {
        return productDao.selectProducts();
    }


    public int itemCreate(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        return productDao.insertProduct(product).getId();
    }



    public void itemUpdate(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        productDao.updateProduct(product);
    }



    public void itemDelete(Integer id) {
        productDao.deleteProdect(id);
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
