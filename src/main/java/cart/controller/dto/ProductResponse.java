package cart.controller.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private long id;
    private String name;
    private String image;
    private BigDecimal price;

    private ProductResponse() {
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
