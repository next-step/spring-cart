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

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private int price;

    public Product(String name, String image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Product(Long id, String name, String image, int price) {
        this(name, image, price);
        this.id = id;
    }

    public Product() {}
}
