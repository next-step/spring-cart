package cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.Cart;
import cart.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    // TODO: 인증기능추가
    @DeleteMapping("/cart/{id}")
    public String delete(@PathVariable("id") long id) {
        cartService.delete(id);
        return "redirect:/cart";
    }

    @PostMapping(value = "/cart")
    // public ResponseEntity<Cart> create(@RequestHeader(value = "Authorization")
    // String authorization,
    // @RequestBody long productId)
    public ResponseEntity<Cart> create(long productId) {
        String authorization = "1";
        System.out.println("++++test");
        return ResponseEntity.ok().body(cartService.create(authorization, productId));
    }
}
