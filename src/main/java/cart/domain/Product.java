package cart.domain;

public class Product {

    private int id;
    private String name;
    private Integer price;
    private String imagename;

    public Product() {
    }

    public Product(String name, Integer price, String imagename) {
        this.name = name;
        this.price = price;
        this.imagename = imagename;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
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
