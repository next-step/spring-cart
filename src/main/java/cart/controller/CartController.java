package cart.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import cart.domain.AuthInfo;
import cart.domain.Member;
import cart.domain.Product;
import cart.service.AuthService;
import cart.service.CartService;
import cart.service.MemberService;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final AuthService authService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String cart() {
        return "/cart";
    }

    @GetMapping("/cart/{id}")
    public String create(@RequestHeader String authorization, @PathVariable("id") long id) throws AccessDeniedException {
        AuthInfo authInfo = authService.createAuth(authorization);
        Member member = memberService.getMember(authInfo.getEmail(), authInfo.getPassword());
        Product product = productService.getProduct(id);
        cartService.create(member, product);

        return "/cart";
    }
}
