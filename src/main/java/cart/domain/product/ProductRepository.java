package cart.domain.product;

import cart.domain.product.model.ProductModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static final List<ProductModel> PRODUCTS = new ArrayList<>();
    private static final AtomicLong id = new AtomicLong(0);

    public Long save(ProductModel productModel) {
        long productId = id.incrementAndGet();
        productModel.addId(productId);
        PRODUCTS.add(productModel);
        return productId;
    }

    public List<ProductModel> findAll() {
        return new ArrayList<>(PRODUCTS);
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

    public Map<Long, ProductModel> findByIds(List<Long> productIds) {
        return PRODUCTS.stream()
            .filter(productModel -> productIds.contains(productModel.getId()))
            .collect(Collectors.toMap(ProductModel::getId, Function.identity()));
    }
}
