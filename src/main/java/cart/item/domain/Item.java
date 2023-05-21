package cart.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;
    private Long memberId;
    private Long productId;
    private LocalDateTime createdAt;
}
