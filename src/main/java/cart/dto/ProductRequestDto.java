package cart.dto;

import cart.domain.Product;

public class ProductRequestDto {
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
}
