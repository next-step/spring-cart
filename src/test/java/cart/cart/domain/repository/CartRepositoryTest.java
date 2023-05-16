package cart.cart.domain.repository;

import cart.cart.domain.entity.Cart;
import cart.cart.domain.vo.CartId;
import cart.cart.domain.vo.Count;
import cart.cart.persistence.CartDao;
import cart.member.domain.entity.Member;
import cart.member.domain.repository.MemberRepository;
import cart.member.domain.vo.MemberId;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import cart.product.domain.vo.ProductId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;


@JdbcTest
class CartRepositoryTest {

    private CartRepository cartRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProductRepository productRepository;
    @Autowired
    private DataSource dataSource;
    @BeforeEach
    void setUp() {
        cartRepository = new CartDao(memberRepository, productRepository, dataSource);
    }

    @Test
    @DisplayName("Cart 수정에 성공한다")
    void update_success() {
        Long id = 1L;
        int count = 2;
        Member member = Member.builder()
                .id(new MemberId(1L))
                .build();
        Product product = Product.builder()
                .id(new ProductId(1L))
                .build();
        Cart cart = Cart.builder()
                .id(new CartId(id))
                .member(member)
                .product(product)
                .count(new Count(count))
                .build();

        int affectedRow = cartRepository.update(cart);

        assertThat(affectedRow).isEqualTo(1);
    }

}
