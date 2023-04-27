package cart.controller;

import cart.domain.Member;
import cart.domain.Product;
import cart.infrastructure.AuthorizationExtractor;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.AuthService;
import cart.service.CartService;
import cart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartApiController {
    private AuthorizationExtractor<Member> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
    private final AuthService authService;
    private final CartService cartService;
    @PostMapping("/cart/add/{productId}")
    public ResponseEntity addCart(HttpServletRequest request, @PathVariable String productId) {
        Member member = basicAuthorizationExtractor.extract(request);
        cartService.addCart(authService.toCart(member, Long.parseLong(productId)));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cart/show")
    public List<Product> getCart(HttpServletRequest request) {
        Member member = basicAuthorizationExtractor.extract(request);
        return cartService.getCart(authService.toMember(member));
    }

    @DeleteMapping("/cart/delete/{productId}")
    public ResponseEntity deleteCart(HttpServletRequest request, @PathVariable String productId) {
        Member member = basicAuthorizationExtractor.extract(request);
        cartService.deleteCart(authService.toCart(member, Long.parseLong(productId)));
        return new ResponseEntity(HttpStatus.OK);
    }
}
