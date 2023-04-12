package cart.cartItem.model;

import cart.product.model.ProductEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "product_id")
    private Long productId;

    @OneToOne
    @JoinColumn(name = "id")
    private ProductEntity productEntity;

    public CartItemEntity (Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }
}
