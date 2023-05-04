package cart.domain;

public interface ProductRepository {

    Products findAll();

    void save(Product product);

}
