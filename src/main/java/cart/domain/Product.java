package cart.domain;

public class Product {
    private Long id;
    private int price;
    private String name;
    private String imageUrl;

    public Product(Long id, int price, String name, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
