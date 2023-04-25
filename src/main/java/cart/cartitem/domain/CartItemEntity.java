package cart.cartitem.domain;

import java.util.Objects;

public class CartItemEntity {
    private final CartItemId id;
    private final CartItem cartItem;

    public CartItemEntity(CartItemId id, CartItem cartItem) {
        this.id = id;
        this.cartItem = cartItem;
    }

    public Long getIdValue() {
        return id.getValue();
    }

    public String getProductNameValue() {
        return cartItem.getProductNameValue();
    }

    public String getProductImageValue() {
        return cartItem.getProductImageValue();
    }

    public Long getProductPriceValue() {
        return cartItem.getProductPriceValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemEntity that = (CartItemEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
