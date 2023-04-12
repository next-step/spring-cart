package cart.cartItem;

import cart.cartItem.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByMemberId(Long memberId);
}
