package cart.product;

import java.util.Objects;

public class ProductEntity {
    private final ProductId id;
    private final Product product;

    public ProductEntity(ProductId id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Long getIdValue() {
        return id.getValue();
    }

    public String getNameValue() {
        return product.getNameValue();
    }

    public String getImageValue() {
        return product.getImageValue();
    }

    public Long getPriceLongValue() {
        return product.getPriceLongValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productEntity = (ProductEntity) o;
        return Objects.equals(id, productEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
