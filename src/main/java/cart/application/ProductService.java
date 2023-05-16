package cart.application;

import cart.application.dto.CreateProductRequest;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Long register(CreateProductRequest request) {
    return this.productRepository.save(request.getName(), request.getPrice(), request.getImageUrl());
  }
}
