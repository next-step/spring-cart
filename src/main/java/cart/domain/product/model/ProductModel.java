package cart.domain.product.model;

public class ProductModel {

    private Long id;
    private String name;
    private String imageUrl;
    private Long price;

    public ProductModel(Long id, String name, String imageUrl, Long price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductModel(String name, String imageUrl, Long price) {
        this(null, name, imageUrl, price);
    }

    public void update(String name, String imageUrl, Long price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public void addId(Long id) {
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
