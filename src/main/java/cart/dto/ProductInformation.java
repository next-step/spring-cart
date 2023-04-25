package cart.dto;

import cart.product.Product;

public class ProductInformation {
    private final Long id;
    private final String name;
    private final String image;
    private final Long price;

    public ProductInformation(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static ProductInformation fromProduct(Product product) {
        return new ProductInformation(
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
