package cart.controller;

import cart.domain.Cart;
import cart.infrastructure.AuthenticationPrincipal;
import cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @PostMapping("/cart/add")
    public ResponseEntity add(@AuthenticationPrincipal String email, @RequestBody Cart cart) {
        cartService.addCart(email, cart);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/cart/remove")
    public ResponseEntity remove(@AuthenticationPrincipal String email, @RequestBody Cart cart) {
        cartService.removeCart(email, cart);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PostMapping("/cart/list")
    public ResponseEntity<List<Cart>> cart(@AuthenticationPrincipal String email){
        return ResponseEntity.ok(cartService.showCart(email));
    }
}
