package cart.dto.product;

import cart.domain.Product;

public class ProductCreateRequestDto {
	Long id;
	private String name;
	private String image;
	private Long price;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public Long getPrice() {
		return price;
	}

	public Product toProduct() {
		return Product.of(id, name, image, price);
	}

	public static ProductCreateRequestDto fromProduct(Product product) {
		ProductCreateRequestDto requestDto = new ProductCreateRequestDto();
		requestDto.id = product.getId();
		requestDto.name = product.getName();
		requestDto.image = product.getImage();
		requestDto.price = product.getPrice();
		return requestDto;
	}
}
