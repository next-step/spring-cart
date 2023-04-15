package cart.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.AuthInfo;
import cart.domain.Cart;
import cart.domain.Member;
import cart.service.AuthService;
import cart.service.CartService;
import cart.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;
    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping(value = "/cart/list")
    public ResponseEntity<List<Cart>> cartList(@RequestHeader String authorization) throws AccessDeniedException {
        AuthInfo authInfo = authService.createAuth(authorization);
        Member member = memberService.getMember(authInfo.getEmail(), authInfo.getPassword());
        return ResponseEntity.ok().body(cartService.getList(member));
    }

    @DeleteMapping("/cart/{id}")
    public String delete(@RequestHeader String authorization, @PathVariable("id") long id) throws AccessDeniedException {
        AuthInfo authInfo = authService.createAuth(authorization);
        cartService.delete(id);
        return "redirect:/cart";
    }
}
