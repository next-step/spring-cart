package cart.data;

import cart.data.entity.CartProduct;

import java.util.List;

public interface ProductRepository {

    void addProduct(CartProduct product);

    List<CartProduct> getProducts();

    CartProduct getProductById(long id);

    void updateProduct(CartProduct cartProduct);

    void removeProduct(long id);
}
