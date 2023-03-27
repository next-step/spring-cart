package cart.controller;

import cart.Exception.AuthorizationException;
import cart.dto.AuthInfo;
import cart.infrastructure.AuthorizationExtractor;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.model.Cart;
import cart.service.AuthService;
import cart.service.CartService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final AuthService authService;

    private final CartService cartService;

    private AuthorizationExtractor<AuthInfo> basicAuthorizationExtractor = new BasicAuthorizationExtractor();

    public CartController(AuthService authService, CartService cartService) {
        this.authService = authService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String cart() {
        return "cart";
    }

    @PostMapping("/list")
    public ResponseEntity<List<Cart>> cart(HttpServletRequest request) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);
        if (authService.checkInvalidLogin(authInfo.getEmail(), authInfo.getPassword())) {
            throw new AuthorizationException();
        }
        return ResponseEntity.ok()
            .body(cartService.cart(authInfo.getEmail(), authInfo.getPassword()).getCarts());
    }

    @PostMapping(value = "/add/{productId}")
    public ResponseEntity<Integer> add(HttpServletRequest request, @PathVariable int productId) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);
        if (authService.checkInvalidLogin(authInfo.getEmail(), authInfo.getPassword())) {
            throw new AuthorizationException();
        }
        Cart cart = new Cart(authInfo.getEmail(), authInfo.getPassword(), productId);
        return ResponseEntity.ok().body(cartService.insert(cart));
    }

    @PostMapping(value = "/delete/{productId}")
    public ResponseEntity<Integer> delete(HttpServletRequest request, @PathVariable int productId) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);
        if (authService.checkInvalidLogin(authInfo.getEmail(), authInfo.getPassword())) {
            throw new AuthorizationException();
        }
        Cart cart = new Cart(authInfo.getEmail(), authInfo.getPassword(), productId);
        return ResponseEntity.ok().body(cartService.delete(cart));
    }

}
