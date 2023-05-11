package cart.cart.domain.dto;

import cart.cart.domain.entity.Cart;
import cart.member.domain.entity.Member;
import cart.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private Member member;
    private Product product;
    private int count;

    public static CartDto from(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .member(cart.getMember())
                .product(cart.getProduct())
                .build();
    }
}
