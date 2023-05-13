package cart.cart.domain.entity;

import cart.cart.domain.vo.CartId;
import cart.cart.domain.vo.Count;
import cart.member.domain.entity.Member;
import cart.product.domain.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    @DisplayName("동등성 비교, hashcode 비교가 true 를 반환한다")
    void equalsAndHashCode_true() {
        Cart cart1 = new Cart(new CartId(1L), new Member(1L), new Product(1L), new Count(1));
        Cart cart2 = new Cart(new CartId(1L), new Member(1L), new Product(1L), new Count(1));

        boolean equalsResult = cart1.equals(cart2);
        boolean hashcodeResult = cart1.hashCode() == cart2.hashCode();

        assertThat(equalsResult).isTrue();
        assertThat(hashcodeResult).isTrue();
    }

    @Test
    @DisplayName("동등성 비교, hashcode 비교가 false 를 반환한다")
    void equalsAndHashCode_false() {
        Cart cart1 = new Cart(new CartId(1L), new Member(1L), new Product(1L), new Count(1));
        Cart cart2 = new Cart(new CartId(2L), new Member(1L), new Product(1L), new Count(1));

        boolean equalsResult = cart1.equals(cart2);
        boolean hashcodeResult = cart1.hashCode() == cart2.hashCode();

        assertThat(equalsResult).isFalse();
        assertThat(hashcodeResult).isFalse();
    }

}
