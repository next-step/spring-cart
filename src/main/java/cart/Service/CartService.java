package cart.Service;


import cart.dao.CartDAO;
import cart.dao.ProductDAO;
import cart.domain.Cart;
import cart.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public CartService(ProductDAO productDAO, CartDAO cartDAO) {
        this.productDAO = productDAO;
        this.cartDAO = cartDAO;
    }

    public int addCart(Cart cart) {
        return cartDAO.insertCart(cart);
    }

    public int removeCart(Cart cart) {
        return cartDAO.deleteCart(cart);
    }

    public List<Cart> productCarts(Cart cart) {

        List<Cart> carts = cartDAO.selectCarts(cart);
        carts.stream().map(res -> {
            Product product = productDAO.selectProducts(res);
            res.setProduct(product);
            return res;
        }).collect(Collectors.toList());

        return carts;
    }


}
