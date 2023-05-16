package cart.application;

import cart.application.dto.FindProductResponse;
import cart.application.dto.ProductRequest;
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

  public Long register(ProductRequest request) {
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

  public FindProductResponse modifyProduct(Long id, ProductRequest request) {
    String name = request.getName();
    int price = request.getPrice();
    String imageUrl = request.getImageUrl();

    this.productRepository.update(id, name, price, imageUrl);

    return FindProductResponse.builder().id(id).name(name).price(price).imageUrl(imageUrl).build();
  }
}
