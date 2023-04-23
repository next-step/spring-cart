package cart.domain;

import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String image;

    private int price;

    public Product(String name, String image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Product(long id, String name, String image, int price) {
        this(name, image,price);
        this.id = id;
    }

    public Product() {}
}
