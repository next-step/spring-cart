package cart.product.application;

import cart.product.application.usecase.GetProductInformationUseCase;
import cart.product.application.dto.ProductInformation;
import cart.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
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
}
