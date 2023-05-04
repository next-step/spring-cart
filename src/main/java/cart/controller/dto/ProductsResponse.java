package cart.controller.dto;

import java.util.List;

public class ProductsResponse {

    private List<ProductResponse> products;

    private ProductsResponse(List<ProductResponse> products) {
        this.products = products;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

}
