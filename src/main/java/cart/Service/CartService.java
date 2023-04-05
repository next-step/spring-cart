package cart.Service;


import cart.dao.CartDAO;
import cart.dao.ProductDAO;
import cart.domain.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public CartService(ProductDAO productDAO, CartDAO cartDAO) {
        this.productDAO = productDAO;
        this.cartDAO = cartDAO;
    }

    public int add(Cart cart) {
        return cartDAO.insertCart(cart);
    }

    public int remove(Cart cart) {

        return cartDAO.deleteCart(cart);
    }

    public List<Cart> productCarts(String email) {

        List<Cart> carts = cartDAO.findCartsByEmail(email);
        return carts;
    }


}
