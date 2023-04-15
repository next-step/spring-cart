package cart.controller;

import cart.auth.AuthData;
import cart.auth.AuthInfo;
import cart.controller.response.CartResponse;
import cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartApiController {

    private final CartService cartService;

    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<Void> addCart(
            @AuthInfo AuthData authData,
            @PathVariable Long productId) {

        if (authData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartService.save(authData, productId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartResponse>> addCart(@AuthInfo AuthData authData) {

        if (authData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().body(cartService.findAll(authData));
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCart(@AuthInfo AuthData authData, @PathVariable Long id) {
        if (authData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        cartService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
