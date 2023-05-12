package cart.cart.web;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public List<CartDto> readMemberCarts(@RequestParam Long memberId) {
        return cartService.getCartsByMemberId(memberId);
    }
}
