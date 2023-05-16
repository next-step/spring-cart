package cart.data;

import cart.data.entity.CartProduct;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void addProduct(CartProduct product);

    List<CartProduct> getProducts();

    Optional<CartProduct> getProductById(long id);

    void updateProduct(CartProduct cartProduct);

    int removeProduct(long id);
}
