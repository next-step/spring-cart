package cart.cart.infra;

import cart.cart.domain.Cart;
import cart.cart.domain.CartRepository;
import cart.cart.domain.Carts;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartDao extends NamedParameterJdbcDaoSupport implements CartRepository {

    private static final String TABLE_NAME = "cart";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"member_id", "product_id"};
    private static final CartRowMapper ROW_MAPPER = new CartRowMapper();
    private final SimpleJdbcInsert simpleJdbcInsert;

    public CartDao(DataSource dataSource) {
        setDataSource(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID)
                .usingColumns(COLUMNS);
    }

    @Override
    public Carts findAll() {
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        List<Cart> carts = getNamedParameterJdbcTemplate().query(query, ROW_MAPPER);

        return new Carts(carts);
    }

    @Override
    public Carts findByMemberId(long memberId) {
        final String query = String.format("SELECT * FROM %s WHERE member_id = :memberId", TABLE_NAME);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("memberId", memberId);
        List<Cart> carts = getNamedParameterJdbcTemplate().query(query, namedParameters, ROW_MAPPER);

        return new Carts(carts);
    }

    @Override
    public void addProduct(Cart cart) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(cart);
        simpleJdbcInsert.executeAndReturnKey(parameterSource);
    }

    @Override
    public void deleteCart(Cart cart) {
        final String query = String.format("DELETE FROM %s WHERE member_id = :memberId AND product_id = :productId",
                TABLE_NAME);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(cart);

        getNamedParameterJdbcTemplate().update(query, namedParameters);
    }

}
