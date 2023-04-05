package cart.admin.application;

import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import cart.admin.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {


    private final AdminProductDao adminProductDao;

    public AdminService(AdminProductDao adminProductDao) {
        this.adminProductDao = adminProductDao;
    }

    public int createProduct(ProductDto productDto) {

        Product product = new Product(productDto.getName(), productDto.getImageUrl(), productDto.getPrice());

        return adminProductDao.insertProduct(product).getId();
    }

    public void updateProduct(ProductDto productDto) {

        Product product = new Product(productDto.getId(), productDto.getName(), productDto.getImageUrl(), productDto.getPrice());

        adminProductDao.updateProduct(product);
    }

    public void deleteProduct(int id) {
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
