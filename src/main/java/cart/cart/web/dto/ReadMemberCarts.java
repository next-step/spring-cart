package cart.cart.web.dto;

import cart.cart.domain.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReadMemberCarts {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long memberId;
        private List<CartDto> carts;
    }
}
