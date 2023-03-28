package cart.domain;

public class Cart {

    private int id;
    private String name;
    private int price;
    private String imagename;

    public Cart(String name, int price, String imagename) {
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

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
