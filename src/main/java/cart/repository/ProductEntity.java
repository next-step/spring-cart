package cart.repository;

public class ProductEntity {
    private Long id;
    private final String name;
    private final String imageUrl;
    private final Long price;

    public ProductEntity(Long id, String name, String imageUrl, Long price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductEntity(String name, String imageUrl, Long price) {
        this(0L, name, imageUrl, price);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPrice() {
        return price;
    }
}
