package cart.admin.application;

import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import cart.admin.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class AdminService {


    private final AdminProductDao adminProductDao;

    public AdminService(AdminProductDao adminProductDao) {
        this.adminProductDao = adminProductDao;
    }

    public int createProduct(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        return adminProductDao.insertProduct(product).getId();
    }

    public void updateProduct(Supplier<Product> productSupplier) {

        Product product = productSupplier.get();

        adminProductDao.updateProduct(product);
    }

    public void deleteProduct(Integer id) {
        adminProductDao.deleteProdect(id);
    }

    public List<Product> selectAllProduct() {
        return adminProductDao.selectProducts();
    }

    public Product selectOneProduct(int id) {
        return adminProductDao.selectOneProduct(id);
    }


    @Override
    public String toString() {
        return "AdminService{" +
                "adminProductDao=" + adminProductDao +
                '}';
    }
}
