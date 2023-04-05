package cart.controller;


import cart.Service.CartService;
import cart.config.AuthenticationPrincipal;
import cart.domain.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "cart";
    }

    @PostMapping("/cart/add")
    public ResponseEntity<Integer> add(@AuthenticationPrincipal String email, @RequestBody Cart cart, HttpServletRequest request) {
        cart.setEmail(email);
        return ResponseEntity.ok(cartService.add(cart));
    }

    @PostMapping("/cart/remove")
    public ResponseEntity<Integer> remove(@AuthenticationPrincipal String email, @RequestBody Cart cart, HttpServletRequest request) {
        cart.setEmail(email);
        return ResponseEntity.ok(cartService.remove(cart));
    }

    @PostMapping("/cart/list")
    public ResponseEntity<List<Cart>> list(@AuthenticationPrincipal String email, HttpServletRequest request) {
        return ResponseEntity.ok(cartService.productCarts(email));
    }


}