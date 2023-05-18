package cart.domain;

public class Product {
	private Long id;
	private String name;
	private String image;
	private Long price;

	public Product() {
	}

	public Product(Long id, String name, String image, Long price) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public static Product of(Long id, String name, String image, Long price) {
		return Product.builder()
			.id(id)
			.name(name)
			.image(image)
			.price(price)
			.build();
	}

	public static ItemBuilder builder() {
		return new ItemBuilder();
	}

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

	public void updateId(long id) {
		this.id = id;
	}

	public void update(String name, String image, Long price) {
		this.name = name;
		this.image = image;
		this.price = price;
	}

	public static class ItemBuilder {
		private Long id;
		private String name;
		private String image;
		private Long price;

		public ItemBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public ItemBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ItemBuilder image(String image) {
			this.image = image;
			return this;
		}

		public ItemBuilder price(Long price) {
			this.price = price;
			return this;
		}

		public Product build() {
			return new Product(id, name, image, price);
		}
	}

}
