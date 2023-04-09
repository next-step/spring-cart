package cart.service;

import cart.dao.ProductDao;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdunctService {
    private ProductDao productDAO;

    public ProdunctService(ProductDao productDAO) {
        this.productDAO = productDAO;
    }

    public void createProduct(Product product) {
        productDAO.insertProduct(product.getName(), product.getImageUrl(), product.getPrice());
    }

    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product.getId());
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public List<Product> showProduct(){
        List<Product> result = productDAO.selectProduct();
        return result;
    }

}
