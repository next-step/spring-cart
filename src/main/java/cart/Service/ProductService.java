package cart.Service;


import cart.dao.ProductDAO;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public int addProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    public int removeProduct(Product product) {
        return productDAO.deleteProduct(product);
    }
    
    public int changeProduct(Product product) {
        return productDAO.updateProduct(product);
    }


    public List<Product> productList() {
        List<Product> result = productDAO.selectProducts();
        return result;
    }


}
