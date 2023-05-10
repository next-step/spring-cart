package cart.business;

import cart.business.domain.Product;
import cart.data.ProductRepository;
import cart.data.entity.CartProduct;
import cart.presentation.dto.RequestProduct;
import cart.presentation.dto.ViewProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final ProductRepository productRepository;

    public AdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(RequestProduct dto) {
        Product product = new Product(dto);
        CartProduct cartProduct = new CartProduct(product);
        productRepository.addProduct(cartProduct);
    }

    public List<ViewProduct> getViewProducts() {
        return productRepository.getProducts().stream().map(ViewProduct::new)
                .collect(Collectors.toList());
    }

    public void updateProduct(long id, RequestProduct product) {
        CartProduct productEntity = productRepository.getProductById(id);
        productEntity.modifyProduct(product);
        productRepository.updateProduct(productEntity);
    }

    public void deleteProduct(long id) {
        productRepository.removeProduct(id);
    }
}
