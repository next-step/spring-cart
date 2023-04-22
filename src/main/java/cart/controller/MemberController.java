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
public class MemberController {

    private final Carts carts;
    private final Products products;

    public MemberController(Carts carts, Products products) {
        this.carts = carts;
        this.products = products;
    }

    @DeleteMapping("/member/cart/{id}")
    public ResponseEntity<Void> removeCart(@PathVariable Long id) {
        final Cart cart = carts.findById(id).orElseThrow(() -> new CartException("존재하지 않는 장바구니입니다."));
        carts.remove(cart);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> getCart(@MemberAuth Member member) {
        final List<Cart> memberCarts = carts.findAllByMember(member);
        return ResponseEntity.ok(CartResponse.lisfOf(memberCarts));
    }

    @PostMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> addCart(@MemberAuth Member member, @RequestBody CartRequest cart) {
        final Product product = products.findById(cart.getProductId()).orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        if (carts.existsByMemberAndProduct(member, product)) {
            throw new CartException("이미 장바구니에 존재하는 상품입니다.");
        }
        carts.add(new Cart(member, product));
        return ResponseEntity.ok(CartResponse.lisfOf(carts.getAll()));
    }
}
