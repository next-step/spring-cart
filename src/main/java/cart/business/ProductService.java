package cart.business;

import cart.business.domain.Product;
import cart.data.ProductRepository;
import cart.data.entity.CartProduct;
import cart.presentation.dto.RequestProduct;
import cart.presentation.dto.ViewProduct;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(RequestProduct dto) {
        Product product = new Product(dto);
        CartProduct cartProduct = new CartProduct(product);
        productRepository.addProduct(cartProduct);
    }

    public List<ViewProduct> getProducts() {
        return productRepository.getProducts().stream().map(ViewProduct::new)
                .collect(Collectors.toList());
    }

    public void updateProduct(long id, RequestProduct product) {
        Optional<CartProduct> optionalProduct = productRepository.getProductById(id);
        CartProduct cartProduct =
            optionalProduct.orElseThrow(() -> new RuntimeException(id + "에 해당하는 상품이 없습니다."));
        cartProduct.modifyProduct(product);
        productRepository.updateProduct(cartProduct);
    }

    public void deleteProduct(long id) {
        productRepository.removeProduct(id);
    }
}
