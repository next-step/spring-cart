package cart.domain;

public class Product {

    private Integer id;
    private String name;
    private String imageUrl;
    private Integer price;

    public Product() {
    }

    public Product(Integer id, String name, String imageUrl, Integer price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product(String name, String imageUrl, Integer price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}