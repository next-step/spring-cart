package cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cart.domain.Cart;
import cart.domain.Member;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);

    List<Cart> findAll();
    
    List<Cart> findByMember(Member member);
}
