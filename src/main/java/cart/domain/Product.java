package cart.domain;

import cart.exception.ProductException;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String imageUrl;
    private int price;

    public Product(Long id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product(String name, String imageUrl, int price) {
        this(null, name, imageUrl, price);
    }

    public void update(Product product) {
        if(!this.id.equals(product.id)) {
            throw new ProductException();
        }
        this.name = product.name;
        this.imageUrl = product.imageUrl;
        this.price = product.price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name)
            && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, price);
    }
}
