package cart.cart.persistence;

import cart.cart.domain.entity.Cart;
import cart.cart.domain.repository.CartRepository;
import cart.member.domain.repository.MemberRepository;
import cart.product.domain.repository.ProductRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartDao implements CartRepository {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public CartDao(MemberRepository memberRepository,
                   ProductRepository productRepository,
                   DataSource dataSource) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("CART")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public Cart findById(Long cartId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", cartId);
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from cart where id = :id",
                    parameters,
                    new CartRowMapper(memberRepository, productRepository)
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new IllegalArgumentException("cartId에 해당하는 정보가 없습니다");
        }
    }

    @Override
    public Optional<Cart> findByProductIdAndMemberId(Long productId, Long memberId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("product_id", productId);
        parameters.put("member_id", memberId);

        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(
                    "select * from cart where member_id = :member_id and " +
                            " product_id = :product_id",
                    parameters,
                    new CartRowMapper(memberRepository, productRepository)
            ));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cart> findByMemberId(Long memberId) {
        return namedParameterJdbcTemplate.query(
                "select * from cart where member_id = :member_id",
                new MapSqlParameterSource("member_id", memberId),
                new CartRowMapper(memberRepository, productRepository)
        );
    }

    @Override
    public Long insert(Cart cart) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", cart.getMemberId());
        parameters.put("product_id", cart.getProductId());
        parameters.put("count", cart.getCount());

        Number number = insertActor.executeAndReturnKey(parameters);
        return number.longValue();
    }

    @Override
    public int update(Cart cart) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", cart.getMemberId());
        parameters.put("product_id", cart.getProductId());
        parameters.put("count", cart.getCount());
        parameters.put("id", cart.getId());

        return namedParameterJdbcTemplate.update(
                "update cart " +
                        " set member_id = :member_id," +
                        " product_id = :product_id," +
                        " count = :count " +
                        " where id = :id",
                parameters
        );
    }

    @Override
    public int delete(Cart cart) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", cart.getId());

        return namedParameterJdbcTemplate.update(
                "delete from cart where id = :id",
                parameters
        );
    }
}
