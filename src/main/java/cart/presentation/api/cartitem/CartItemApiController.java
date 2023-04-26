package cart.presentation.api.cartitem;

import cart.domain.cartitem.CartItemService;
import cart.presentation.api.cartitem.request.AddCartItemRequest;
import cart.presentation.api.cartitem.response.CartItemResponse;
import cart.presentation.config.AuthInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart-items")
public class CartItemApiController {

    private final CartItemService cartItemService;

    public CartItemApiController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> carItems(AuthInfo authInfo) {
        return ResponseEntity.ok(cartItemService.readMemberCartProducts(authInfo.getEmail()).entrySet().stream()
            .flatMap(entry -> entry.getValue().stream()
                .map(value -> new CartItemResponse(entry.getKey(), value.getName(), value.getPrice()))
            )
            .collect(Collectors.toList())
        );
    }

    @PutMapping
    public ResponseEntity<Void> addProductInCart(@RequestBody AddCartItemRequest request, AuthInfo authInfo) {
        cartItemService.createCartItem(authInfo.getEmail(), request.getProductId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeCart(@PathVariable Long cartItemId, AuthInfo authInfo) {
        cartItemService.removeCartItem(authInfo.getEmail(), cartItemId);
        return ResponseEntity.ok().build();
    }
}
