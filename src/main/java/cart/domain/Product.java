package cart.domain;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
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

    public Product edit(ProductEditRequest productEditRequest) {
        this.name = productEditRequest.getName();
        this.image = productEditRequest.getImage();
        this.price = productEditRequest.getPrice();
        return this;
    }

    public static Product of(ProductRequest productRequest) {
        return new Product(productRequest.getName(), productRequest.getImage(), productRequest.getPrice());
    }
}
