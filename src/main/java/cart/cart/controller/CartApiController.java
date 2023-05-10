package cart.cart.controller;

import cart.auth.authentication.Auth;
import cart.auth.authentication.Authentication;
import cart.cart.application.CartService;
import cart.cart.controller.dto.AddProductRequest;
import cart.cart.controller.dto.CartProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartApiController {

    private final CartService cartService;

    public CartApiController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartProductResponse>> getCart(@Authentication Auth auth) {
        List<CartProductResponse> response = cartService.findCartProduct(auth.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> addCart(@Authentication Auth auth, @RequestBody AddProductRequest request) {
        cartService.addProduct(auth, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteCartProduct(@Authentication Auth auth, @PathVariable Long productId) {
        cartService.deleteCartProduct(auth, productId);

        return ResponseEntity.noContent().build();
    }

}
