package cart.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Product {
    private final Long id;
    private final String name;
    private final String image;
    private final long price;

    public Product(Long id, String name, String image, long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Product(long id, String name, String image, long price) {
        this(Long.valueOf(id), name, image, price);
    }

    public Product() {
        this(0, "", "", 0);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getPrice() {
        return price;
    }

    public static Map<String, Object> getInsertParameter(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        if (product.id != null) {
            parameters.put("id", product.id);
        }
        parameters.put("name", product.name);
        parameters.put("image", product.image);
        parameters.put("price", product.price);
        parameters.put("created_at", LocalDateTime.now());
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
