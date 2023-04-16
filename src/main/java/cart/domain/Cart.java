package cart.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart {
    private final Long id;
    private final long memberId;
    private final long productId;

    public Cart(Long id, long memberId, long productId) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
    }

    public Cart(long memberId, long productId) {
        this(null, memberId, productId);
    }

    public long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getProductId() {
        return productId;
    }
    public static Map<String, Object> getInsertParameter(Cart cart) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", cart.memberId);
        parameters.put("product_id", cart.productId);
        if (cart.id != null) {
            parameters.put("id", cart.id);
        }
        return parameters;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return memberId == cart.memberId && productId == cart.productId && Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, productId);
    }
}
