package cart.service;

import cart.controller.usecase.GetProductInformationUseCase;
import cart.dto.ProductInformation;
import cart.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class GetProductService implements GetProductInformationUseCase {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductInformation> getProductInformations() {
        return productRepository.findAll()
                .stream()
                .map(ProductInformation::fromProduct)
                .collect(Collectors.toList());
    }
}
