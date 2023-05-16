package cart.cart.domain.vo;


import java.util.Objects;

public class CartId {
    private Long id;

    public CartId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(id, cartId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
