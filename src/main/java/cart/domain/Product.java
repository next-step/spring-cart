package cart.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author minsukim on 2023/05/07
 * @project jwp-cart
 * @description
 */
@Getter
@Builder
public class Product {

    private int id;
    private String name;
    private String image;
    private int price;
    private LocalDateTime createdDt;

    public Product(int id, String name, String image, int price, LocalDateTime createdDt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.createdDt = createdDt;
    }
}
