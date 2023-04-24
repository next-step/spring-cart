package cart.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final Long memberId;

    private final Long productId;

    public Cart (Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }
}
