package cart.product.service;

import cart.enums.exceptions.ErrorCode;
import cart.exceptions.ProductNotFoundException;
import cart.product.domain.Product;
import cart.product.dto.request.ProductRequest;
import cart.product.dto.response.ProductResponse;
import cart.product.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductDao productDao;

    public List<ProductResponse> findAll() {
        return productDao.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getImg(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public void insert(ProductRequest productRequest) {
        productDao.insert(productRequest);
    }

    public void update(Long id, ProductRequest productRequest) {
        Product product = productDao.findById(id).orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND_PRODUCT));
        productDao.update(product);
    }

    public void delete(Long id) {
        Product product = productDao.findById(id).orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND_PRODUCT));
        productDao.delete(product.getId());
    }
}
