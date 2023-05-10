package cart.domain.global;

public class Product {
    private Integer id;
    private String name;
    private String image;
    private Integer price;

    public Product(Integer id, String name, String image, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void update(Product product) {
        this.name = product.getName();
        this.image = product.getImage();
        this.price = product.getPrice();
    }
}
