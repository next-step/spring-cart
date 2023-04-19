package cart.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.AuthInfo;
import cart.domain.Cart;
import cart.domain.Member;
import cart.infra.Authorization;
import cart.service.CartService;
import cart.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;
    private final MemberService memberService;

    @PostMapping(value = "/cart/list")
    public ResponseEntity<List<Cart>> cartList(@Authorization AuthInfo authInfo) throws AccessDeniedException {
        Member member = memberService.getMember(authInfo.getEmail(), authInfo.getPassword());
        return ResponseEntity.ok().body(cartService.getList(member));
    }

    @DeleteMapping("/cart/{id}")
    public String delete(@Authorization AuthInfo authInfo, @PathVariable("id") long id) throws AccessDeniedException {
        cartService.delete(id);
        return "redirect:/cart";
    }
}
