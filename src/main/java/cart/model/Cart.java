package cart.model;

public class Cart {

    private String email;
    private String password;
    private int productId;

    private String name;
    private String image;
    private int price;

    public Cart(String email, String password, int productId) {
        this.email = email;
        this.password = password;
        this.productId = productId;
    }

    public Cart(String email, String password, int productId, String name, String image,
        int price) {
        this.email = email;
        this.password = password;
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
