package cart.controller;

import cart.dto.AuthInfo;
import cart.model.Cart;
import cart.service.AuthService;
import cart.service.CartService;
import java.nio.file.AccessDeniedException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class CartController {

    private final AuthService authService;

    private final CartService cartService;

    public CartController(AuthService authService, CartService cartService) {
        this.authService = authService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @PostMapping("/cart/list")
    public ResponseEntity<List<Cart>> cart(
        @RequestHeader(value = "Authorization") String Authorization)
        throws AccessDeniedException {
        AuthInfo authInfo = authService.checkBasicAuth(Authorization);
        return ResponseEntity.ok()
            .body(cartService.list(authInfo.getEmail(), authInfo.getPassword()).getCarts());
    }

    @PostMapping(value = "/cart/add/{productId}")
    public ResponseEntity<Integer> add(@RequestHeader(value = "Authorization") String Authorization,
        @PathVariable int productId)
        throws AccessDeniedException {
        AuthInfo authInfo = authService.checkBasicAuth(Authorization);
        Cart cart = new Cart(authInfo.getEmail(), authInfo.getPassword(), productId);
        return ResponseEntity.ok().body(cartService.insert(cart));
    }

    @PostMapping(value = "/cart/delete/{productId}")
    public ResponseEntity<Integer> delete(
        @RequestHeader(value = "Authorization") String Authorization, @PathVariable int productId)
        throws AccessDeniedException {
        AuthInfo authInfo = authService.checkBasicAuth(Authorization);
        Cart cart = new Cart(authInfo.getEmail(), authInfo.getPassword(), productId);
        return ResponseEntity.ok().body(cartService.delete(cart));
    }

}
