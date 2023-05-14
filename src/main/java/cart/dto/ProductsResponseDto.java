package cart.dto;

import java.util.List;
import java.util.stream.Collectors;

import cart.domain.Product;

public class ProductsResponseDto {
	private final List<ProductResponseDto> products;

	public ProductsResponseDto(List<ProductResponseDto> products) {
		this.products = products;
	}

	public static ProductsResponseDto from(List<Product> productList) {
		List<ProductResponseDto> productResponseDtos = productList.stream()
			.map(ProductResponseDto::from)
			.collect(Collectors.toList());
		return new ProductsResponseDto(productResponseDtos);
	}

	public List<ProductResponseDto> getProducts() {
		return products;
	}
}
