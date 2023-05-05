package cart.controller;

import cart.auth.AuthDto;
import cart.auth.AuthPrincipal;
import cart.service.CartItemService;
import cart.value.ProductsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> findAll(@AuthPrincipal AuthDto authDto) {
        ProductsResponse cartItemsResponse = cartItemService.findAllCartItems(authDto.getId());
        return ResponseEntity.ok(cartItemsResponse);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> delete(@PathVariable Long cartItemId, @AuthPrincipal AuthDto authDto) {
        cartItemService.deleteCartItem(cartItemId, authDto.getId());
        return ResponseEntity.noContent().build();
    }
}
