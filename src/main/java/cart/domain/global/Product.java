package cart.domain.global;

public class Product {
    private static Integer productId = 1;
    private Integer id;
    private String name;
    private String image;
    private Integer price;

    public Product(String name, String image, int price) {
        this.id = Product.productId;
        this.name = name;
        this.image = image;
        this.price = price;
        Product.productId++;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
