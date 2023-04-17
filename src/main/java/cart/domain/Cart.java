package cart.domain;

public class Cart {

    private int id;

    private String email;

    private int productId;

    private Product product;

    public Cart() {
    }

    public Cart(String email, int productId) {
        this.email = email;
        this.productId = productId;
    }

    public Cart(int id, int productId, String name , Integer price, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.product  = new Product(name,price,imageUrl);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProductId() {
        return productId;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + product.getName() + '\'' +
                ", imageUrl='" + product.getImageUrl() + '\'' +
                ", price=" + product.getPrice() +
                '}';
    }
}
