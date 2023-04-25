package cart.product.application;

import cart.product.application.dto.ProductInformation;
import cart.product.application.usecase.GetProductInformationUseCase;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductInformationService implements GetProductInformationUseCase {

    private final ProductRepository productRepository;

    public GetProductInformationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductInformation> getProductInformations() {
        return productRepository.findAll()
                .stream()
                .map(ProductInformation::fromProductEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductInformation getProductInformationById(Long id) {
        return ProductInformation.fromProductEntity(productRepository.findById(id));
    }
}
