package cart.controller;

import cart.auth.MemberAuth;
import cart.domain.*;
import cart.dto.CartRequest;
import cart.dto.CartResponse;
import cart.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MemberController {

    private final Members members;
    private final Carts carts;
    private final Products products;

    public MemberController(Members members, Carts carts, Products products) {
        this.members = members;
        this.carts = carts;
        this.products = products;
    }

    @ResponseBody
    @DeleteMapping("/member/cart/{id}")
    public ResponseEntity<Void> removeCart(@PathVariable Long id) {
        final Cart cart = carts.findById(id).orElseThrow(NoSuchElementException::new);
        carts.remove(cart);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @GetMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> getCart(@MemberAuth Member member) {
        final List<Cart> allByMember = carts.findAllByMember(member);
        return ResponseEntity.ok(CartResponse.lisfOf(allByMember));
    }

    @ResponseBody
    @PostMapping("/member/cart")
    public ResponseEntity<List<CartResponse>> addCart(@MemberAuth Member member, @RequestBody CartRequest cart) {
        final Product product = products.findById(cart.getProductId()).orElseThrow(NoSuchElementException::new);
        if (carts.existsByMemberAndProduct(member, product)) {
            throw new IllegalArgumentException("이미 장바구니에 존재하는 상품입니다.");
        }
        carts.add(new Cart(member, product));
        return ResponseEntity.ok(CartResponse.lisfOf(carts.getAll()));
    }

    @GetMapping("/settings")
    public String index(Model model) {
        final List<Member> members = this.members.getAll();
        model.addAttribute("members", MemberResponse.listOf(members));
        return "settings.html";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart.html";
    }
}
