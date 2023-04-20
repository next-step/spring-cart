package cart.controller;

import cart.domain.Cart;
import cart.infrastructure.AuthenticationPrincipal;
import cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity add(@AuthenticationPrincipal String email, @RequestBody Cart cart) {
        cartService.addCart(email, cart);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/cart")
    public ResponseEntity remove(@AuthenticationPrincipal String email, @RequestParam Long id) {
        cartService.removeCart(email, id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/cart/list")
    public ResponseEntity<List<Cart>> cart(@AuthenticationPrincipal String email){
        return ResponseEntity.ok(cartService.showCart(email));
    }
}
