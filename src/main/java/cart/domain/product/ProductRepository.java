package cart.domain.product;

import cart.domain.product.model.ProductModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private static final List<ProductModel> PRODUCTS = new ArrayList<>();
    private static Long id = 0L;

    @PostConstruct
    public void mockData() {
        save(new ProductModel("mock1", "mockUrl1", 10000L));
        save(new ProductModel("mock2", "mockUrl2", 20000L));
        save(new ProductModel("mock3", "mockUrl3", 30000L));
    }

    public Long save(ProductModel productModel) {
        productModel.addId(++id);
        PRODUCTS.add(productModel);
        return id;
    }

    public List<ProductModel> findAll() {
        return PRODUCTS;
    }

    public void deleteById(Long id) {
        PRODUCTS.removeIf(productModel -> productModel.getId().equals(id));
    }

    public void update(Long id, ProductModel product) {
        PRODUCTS.stream()
            .filter(productModel -> productModel.getId().equals(id))
            .findAny()
            .orElseThrow()
            .update(product.getName(), product.getImageUrl(), product.getPrice());
    }
}
