package cart.product;

import cart.product.model.Product;
import cart.product.model.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findByAll() {

        return productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public Product createProduct(Product product) {

        var productEntity = ProductMapper.INSTANCE.toDao(product);

        return ProductMapper.INSTANCE.toDto(productRepository.save(productEntity));
    }

    @Transactional
    public void deleteProduct(Long id) {

        productRepository.deleteById(id.intValue());
    }
}
