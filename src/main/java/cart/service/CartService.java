package cart.service;

import cart.dao.CartDao;
import cart.domain.Cart;
import cart.dto.AuthInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartDao cartDao;

    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addCart(String mail, Cart cart) {
        cartDao.insertCart(mail, cart);
    }

    public void removeCart(String mail, Long id) {
        cartDao.deleteCart(mail, id);
    }
    public List<Cart> showCart(String mail){
        List<Cart> result = cartDao.selectCart(mail);
        return result;
    }

}
