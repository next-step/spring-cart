package cart.cart.domain.entity;

import cart.cart.domain.vo.CartId;
import cart.cart.domain.vo.Count;
import cart.member.domain.entity.Member;
import cart.member.domain.vo.MemberId;
import cart.product.domain.entity.Product;
import cart.product.domain.vo.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cart {
    private CartId id;
    private Member member;
    private Product product;
    private Count count;

    public Long getId() {
        return id.getId();
    }

    public int getCount() {
        return count.getCount();
    }
}
