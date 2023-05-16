package cart.application;

import cart.application.dto.CreateProductRequest;
import cart.application.dto.FindProductResponse;
import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Long register(CreateProductRequest request) {
    return this.productRepository.save(
        request.getName(), request.getPrice(), request.getImageUrl());
  }

  public List<FindProductResponse> getAllProducts() {
    List<Product> products = this.productRepository.findAll();

    return products.stream()
        .map(
            product ->
                FindProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .build())
        .collect(Collectors.toList());
  }
}
