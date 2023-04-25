package cart.service;

import cart.controller.usecase.GetProductDtoUseCase;
import cart.dto.ProductDto;
import cart.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class GetProductService implements GetProductDtoUseCase {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getProductDtos() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::fromProduct)
                .collect(Collectors.toList());
    }
}
