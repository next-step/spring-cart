package cart.presentation;

import cart.domain.MemberEntity;
import cart.presentation.dto.CartAddRequest;
import cart.presentation.dto.CartDetailResponse;
import cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
    private final CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> addProductInCart(MemberEntity memberEntity, @RequestBody CartAddRequest request) {
        cartService.addProductInCart(memberEntity.getId(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartDetailResponse>> cartItemsForMember(MemberEntity memberEntity) {
        List<CartDetailResponse> responses = cartService.cartProducts(memberEntity.getId());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeCartItem(MemberEntity memberEntity, @PathVariable Long cartId) {
        cartService.removeCart(cartId);
        return ResponseEntity.ok().build();
    }
}
