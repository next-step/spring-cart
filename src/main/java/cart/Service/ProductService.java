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

//    public List<Product> productList(){
//
//    }

}
