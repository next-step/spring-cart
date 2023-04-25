package cart.product;

import java.util.Objects;

public class Product {
    private final ProductId id;
    private ProductName name;
    private ProductImage image;
    private ProductPrice price;

    public Product(Long id, String name, String image, Long price) {
        this(new ProductId(id), new ProductName(name), new ProductImage(image), new ProductPrice(price));
    }

    public Product(ProductId id, ProductName name, ProductImage image, ProductPrice price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Long getIdValue() {
        return id.getValue();
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
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
