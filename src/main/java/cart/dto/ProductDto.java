package cart.dto;

import cart.product.Product;

public class ProductDto {
    private final Long id;
    private final String name;
    private final String image;
    private final Long price;

    public ProductDto(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(
                product.getIdValue(),
                product.getNameValue(),
                product.getImageValue(),
                product.getPriceLongValue()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Long getPrice() {
        return price;
    }
}
