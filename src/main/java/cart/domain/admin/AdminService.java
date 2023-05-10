package cart.domain.admin;

import cart.domain.global.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final List<Product> products;

    public AdminService(List<Product> products) {
        this.products = products;
    }

    public void create(Product product) {
        products.add(new Product(products.get(products.size() - 1).getId() + 1, product.getName(), product.getImage(), product.getPrice()));
    }

    public void update(Product product) {
        products
                .stream()
                .filter(pro -> pro.getId() == product.getId())
                .findFirst()
                .ifPresent(pro -> pro.update(product));
    }

    public void delete(Integer id) {
        products.removeIf(pro -> pro.getId() == id);
    }

}
