package cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private Long id; // 상품 id
    private String name; // 상품 이름
    private String image; // 상품 이미지
    private int price; // 상품 가격

    public Product(String name, String image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Product of(String name, String image, int price) {
        return new Product(name, image, price);
    }

}
