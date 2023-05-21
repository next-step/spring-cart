package cart.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemAddRequest {
    private Long memberId;
    private Long productId;

    public ItemAddRequest setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}
