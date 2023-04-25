package cart.dto;

import cart.product.ProductEntity;

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

    public static ProductInformation fromProductEntity(ProductEntity productEntity) {
        return new ProductInformation(
                productEntity.getIdValue(),
                productEntity.getNameValue(),
                productEntity.getImageValue(),
                productEntity.getPriceLongValue()
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
