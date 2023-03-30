package cart.service;

import cart.dao.ProductDAO;
import cart.model.Product;
import cart.model.Products;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Products productList() {
        return productDAO.list();
    }

    public int insert(Product product) {
        return productDAO.insertProduct(product);
    }

    public Product product(int id) {
        return productDAO.getProduct(id);
    }

    public int update(Product product) {
        return productDAO.updateProduct(product);
    }

    public int delete(int id) {
        return productDAO.deleteProduct(id);
    }
}
