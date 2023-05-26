package cart.controller;

import cart.config.AuthenticationUser;
import cart.controller.dto.request.CartAddRequest;
import cart.controller.dto.response.CartResponse;
import cart.controller.dto.response.CartsResponse;
import cart.domain.User;
import cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartsResponse> getCarts(@AuthenticationUser User user) {
        CartsResponse response = cartService.findAll(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Void> post(@AuthenticationUser User user, @RequestBody CartAddRequest cartAddRequest) {
        CartResponse response = cartService.add(user, cartAddRequest);
        return ResponseEntity.created(URI.create("/carts/" + response.getId())).build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> delete(@AuthenticationUser User user, @PathVariable Long cartId) {
        cartService.delete(user, cartId);
        return ResponseEntity.noContent().build();
    }
}
