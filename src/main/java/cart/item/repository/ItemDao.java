package cart.item.repository;

import cart.item.domain.Item;
import cart.item.dto.request.ItemAddRequest;
import cart.item.dto.request.ItemDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemDao {
    private final JdbcTemplate jdbcTemplate;

    public void insert(ItemAddRequest itemAddRequest) {
        String SQL = "INSERT INTO ITEM(member_id, product_id) VALUES(?, ?)";
        jdbcTemplate.update(SQL, itemAddRequest.getMemberId(), itemAddRequest.getProductId());
    }

    public List<Item> findAllByMemberId(Long id) {
        String SQL = "SELECT * FROM ITEM WHERE id = ?";
        return jdbcTemplate.query(SQL, getItems(), id);
    }

    public void delete(ItemDeleteRequest itemDeleteRequest) {
        String SQL = "DELETE FROM ITEM WHERE member_id = ? AND id = ?";
        jdbcTemplate.update(SQL, itemDeleteRequest.getMemberId(), itemDeleteRequest.getId());
    }

    private RowMapper<Item> getItems() {
        return ((rs, rowNum) -> new Item(rs.getLong("id"), rs.getLong("member_id"), rs.getLong("product_id"), rs.getTimestamp("created_at").toLocalDateTime()));
    }

}
