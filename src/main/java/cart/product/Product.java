package cart.product;

public class Product {
    private final ProductName name;
    private final ProductImage image;
    private final ProductPrice price;

    public Product(String name, String image, Long price) {
        this(new ProductName(name), new ProductImage(image), new ProductPrice(price));
    }

    public Product(ProductName name, ProductImage image, ProductPrice price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public String getImageValue() {
        return image.getValue();
    }

    public Long getPriceLongValue() {
        return price.getLongValue();
    }
}
