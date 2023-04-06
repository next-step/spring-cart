package cart.domain;

public class Product {

    private int id;
    private String name;
    private Integer price;
    private String imagename;

    public Product() {
    }

    public Product(int id, String name, Integer price, String imagename) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagename = imagename;
    }

    public Product(String name, Integer price, String imagename) {
        this.name = name;
        this.price = price;
        this.imagename = imagename;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }


    public String getImagename() {
        return imagename;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imagename='" + imagename + '\'' +
                '}';
    }
}
