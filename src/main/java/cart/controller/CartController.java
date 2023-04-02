package cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.Cart;
import cart.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(value = "/cart")
    public ResponseEntity<List<Cart>> cart(@RequestBody Cart cart) {
        return ResponseEntity.ok().body(cartService.getList());
    }
}
