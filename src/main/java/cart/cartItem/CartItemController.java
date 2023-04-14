package cart.cartItem;

import cart.cartItem.model.CartItem;
import cart.infra.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/cart")
    public String cart(Model model) {
        return "cart";
    }

    @GetMapping("/cart/list")
    public ResponseEntity<List<CartItem>> cartList(@AuthenticationPrincipal Long memberId) {

        var cartItems = cartItemService.findAllByMemberId(memberId);

        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/cart/insert/{productId}")
    public ResponseEntity<CartItem> cartInsert(@AuthenticationPrincipal Long memberId,
                                               @PathVariable Long productId) {

        var result = cartItemService.cartInsert(memberId, productId);

        return  ResponseEntity.ok(result);
    }

    @DeleteMapping("/cart/delete/{id}")
    public String cartDelete(@PathVariable Long id) {

        cartItemService.cartDelete(id);
        return "cart";
    }
}
