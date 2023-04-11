package cart.product.application;

import cart.admin.dao.AdminProductDao;
import cart.admin.domain.Product;
import cart.auth.dto.AuthInfo;
import cart.product.dao.ProductCartDao;
import cart.product.domain.Cart;
import cart.product.dto.ProductCartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCartService {

    private final ProductCartDao productCartDao;

    private final AdminProductDao productDao;

    public ProductCartService(ProductCartDao productCartDao, AdminProductDao productDao) {
        this.productCartDao = productCartDao;
        this.productDao = productDao;
    }

    public void saveCart(AuthInfo authInfo, Integer productId) {
        productCartDao.insertProduct(new Cart(authInfo.getEmail(), productId));
    }

    public List<ProductCartDto> getCarts(AuthInfo authInfo) {

        return productCartDao.selectCarts(authInfo.getEmail());
    }

    public void deleteCart(Integer id) {
        productCartDao.deleteCart(id);
    }

}
