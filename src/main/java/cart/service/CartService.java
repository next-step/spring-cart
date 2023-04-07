package cart.service;

import cart.dao.CartDao;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartDao productDAO;

    public CartService(CartDao productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> productList(){
        List<Product> result = productDAO.selectProductList();
        return result;
    }


}
