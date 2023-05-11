package cart.cart.persistence;

import cart.cart.domain.entity.Cart;
import cart.cart.domain.repository.CartRepository;
import cart.member.domain.repository.MemberRepository;
import cart.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartDao implements CartRepository {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CartDao(MemberRepository memberRepository,
                   ProductRepository productRepository,
                   DataSource dataSource) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Cart> findByMemberId(Long memberId) {
        return namedParameterJdbcTemplate.query(
                "select * from cart where member_id = :member_id",
                new MapSqlParameterSource("member_id", memberId),
                new CartRowMapper(memberRepository, productRepository)
        );
    }
}
