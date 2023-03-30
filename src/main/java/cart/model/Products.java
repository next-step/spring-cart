package cart.model;

import java.util.List;

public class Products {

    List<Product> Products;

    public Products(List<Product> products) {
        Products = products;
    }

    public List<Product> getProducts() {
        return Products;
    }
}
