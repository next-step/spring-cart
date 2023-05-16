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

import java.util.Objects;

@Getter
@AllArgsConstructor
@Builder
public class Cart {
    private final CartId id;
    private final Member member;
    private final Product product;
    private final Count count;

    public void increaseCount(int count) {
        this.count.increase(count);
    }

    public static Cart makeNewCart(Long memberId, Long productId) {
        return Cart.builder()
                .member(new Member(memberId))
                .product(new Product(productId))
                .count(new Count(1))
                .build();
    }

    public Long getId() {
        return id.getId();
    }

    public int getCount() {
        return count.getCount();
    }
    public Long getMemberId() {
        return member.getId();
    }
    public String getMemberEmail() {
        return member.getEmail();
    }
    public Long getProductId() {
        return product.getId();
    }
    public String getProductName() {
        return product.getName();
    }
    public Integer getProductPrice() {
        return product.getPrice();
    }
    public String getProductImage() {
        return product.getImage();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (! (obj instanceof Cart)) return false;
        Cart cart = (Cart) obj;
        return id.equals(cart.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
