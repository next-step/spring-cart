package cart.cart.domain;

public interface CartRepository {

    Carts findAll();

    Carts findByMemberId(long id);

    void addProduct(Cart cart);

    void deleteCart(Cart cart);
}
