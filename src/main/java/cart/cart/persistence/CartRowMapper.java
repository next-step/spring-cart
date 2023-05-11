package cart.cart.persistence;

import cart.cart.domain.entity.Cart;
import cart.cart.domain.vo.CartId;
import cart.cart.domain.vo.Count;
import cart.member.domain.entity.Member;
import cart.member.domain.repository.MemberRepository;
import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CartRowMapper implements RowMapper<Cart> {

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {

        Member member = memberRepository.findById(rs.getLong("member_id"));

        Product product = productRepository.findById(rs.getLong("product_id"));

        return Cart.builder()
                .id(new CartId(rs.getLong("id")))
                .member(member)
                .product(product)
                .count(new Count(rs.getInt("count")))
                .build();
    }
}
