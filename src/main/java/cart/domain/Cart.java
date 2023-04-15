package cart.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Long id;
    private final long memberId;
    private final long productId;

    public Cart(Long id, long memberId, long productId) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
    }

    public Cart(long id, Long productId) {
        this(null, id, productId);
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
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("member_id", cart.getMemberId());
        parameters.put("product_id", cart.getProductId());
        return parameters;
    }
}
