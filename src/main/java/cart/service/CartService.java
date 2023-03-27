package cart.service;

import cart.dao.CartDAO;
import cart.model.Cart;
import cart.model.Carts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private static final Logger logger = LogManager.getLogger(CartService.class);
    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public int insert(Cart cart) {
        return cartDAO.insertCart(cart);
    }

    public Carts cart(String email, String password) {
        return cartDAO.list(email, password);
    }


    public int delete(Cart cart) {
        return cartDAO.deleteCart(cart);
    }
}
