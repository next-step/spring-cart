package cart.controller.dto;

import cart.domain.Product;

import java.math.BigDecimal;

public class ProductResponse {

    private long id;
    private String name;
    private String image;
    private BigDecimal price;

    private ProductResponse() {
    }

    public ProductResponse(long id, String name, String image, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getImage(), product.price());
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

    public BigDecimal getPrice() {
        return price;
    }

}
