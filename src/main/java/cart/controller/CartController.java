package cart.controller;

import cart.auth.MemberAuth;
import cart.domain.*;
import cart.dto.CartRequest;
import cart.dto.CartResponse;
import cart.exception.CartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    private final Carts carts;
    private final Products products;

    public CartController(Carts carts, Products products) {
        this.carts = carts;
        this.products = products;
    }

    @PostMapping("/member/cart")
    public ResponseEntity<CartResponse> create(@MemberAuth Member member, @RequestBody CartRequest cartRequest) {
        final Product product = products.findById(cartRequest.getProductId()).orElseThrow();
        if (carts.existsByMemberAndProduct(member, product)) {
            throw new CartException("이미 장바구니에 존재하는 상품입니다.");
        }
        final Cart cart = carts.add(Cart.of(member, product));
        return ResponseEntity.ok(CartResponse.of(cart, product));
    }

    @GetMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> readAllByMember(@MemberAuth Member member) {
        final List<CartResponse> responses = new ArrayList<>();
        for (Cart cart : carts.findAllByMember(member)) {
            responses.add(CartResponse.of(cart, products.findById(cart.getProductId()).orElseThrow()));
        }
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/member/cart/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final Cart cart = carts.findById(id).orElseThrow();
        carts.remove(cart);
        return ResponseEntity.noContent().build();
    }
}
