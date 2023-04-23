package cart.controller;

import cart.auth.MemberAuth;
import cart.domain.*;
import cart.dto.CartRequest;
import cart.dto.CartResponse;
import cart.exception.CartException;
import cart.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CartResponse> create(@MemberAuth Member member, @RequestBody CartRequest cart) {
        final Product product = products.findById(cart.getProductId()).orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        if (carts.existsByMemberAndProduct(member, product)) {
            throw new CartException("이미 장바구니에 존재하는 상품입니다.");
        }
        final Cart saved = carts.add(new Cart(member, product));
        return ResponseEntity.ok(CartResponse.of(saved));
    }

    @GetMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> readAllByMember(@MemberAuth Member member) {
        final List<Cart> memberCarts = carts.findAllByMember(member);
        return ResponseEntity.ok(CartResponse.lisfOf(memberCarts));
    }

    @DeleteMapping("/member/cart/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final Cart cart = carts.findById(id).orElseThrow(() -> new CartException("존재하지 않는 장바구니입니다."));
        carts.remove(cart);
        return ResponseEntity.noContent().build();
    }
}
