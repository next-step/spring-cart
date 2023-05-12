package cart.cart.web;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.service.CartService;
import cart.cart.web.dto.ReadMemberCarts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/carts")
    public ReadMemberCarts.Response readMemberCarts(@RequestParam Long memberId) {
        return ReadMemberCarts.Response.builder()
                .memberId(memberId)
                .carts(cartService.getCartsByMemberId(memberId))
                .build();
    }
}
