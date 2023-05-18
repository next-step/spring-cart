package cart.dto.product;

public class ProductUpdateRequest {
	private final String name;
	private final String image;
	private final Long price;
	private final Long id;

	public ProductUpdateRequest(String name, String image, Long price, Long id) {
		this.name = name;
		this.image = image;
		this.price = price;
		this.id = id;
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

	public Long getId() {
		return id;
	}

}
