package cart.product;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(image, product.image) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image, price);
    }
}
