package cart.service;

import cart.controller.HomePageProductsUseCase;
import cart.dto.HomePageProduct;
import cart.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class GetProductService implements HomePageProductsUseCase {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<HomePageProduct> getHomePageProducts() {
        return productRepository.findAll()
                .stream()
                .map(HomePageProduct::fromProduct)
                .collect(Collectors.toList());
    }
}
