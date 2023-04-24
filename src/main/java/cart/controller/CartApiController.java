package cart.controller;

import cart.domain.Member;
import cart.domain.Product;
import cart.exception.AuthorizationException;
import cart.infrastructure.AuthorizationExtractor;
import cart.infrastructure.BasicAuthorizationExtractor;
import cart.service.AuthService;
import cart.service.CartService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartApiController {
    private AuthorizationExtractor<Member> basicAuthorizationExtractor = new BasicAuthorizationExtractor();
    private final AuthService authService;
    private final CartService cartService;
    @PostMapping("/cart/add/{productId}")
    public void addCart(HttpServletRequest request, @PathVariable String productId) {
        Member member = basicAuthorizationExtractor.extract(request);
        String email = member.getEmail();
        String password = member.getPassword();

        if (!authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        cartService.addCart(authService.findMember(email).getId(), Long.parseLong(productId));
    }

    @GetMapping("/cart/show")
    public List<Product> getCart(HttpServletRequest request) {
        Member member = basicAuthorizationExtractor.extract(request);
        String email = member.getEmail();
        String password = member.getPassword();

        if (!authService.checkInvalidLogin(email, password)) {
            throw new AuthorizationException();
        }

        return cartService.getCart(authService.findMember(email).getId());
    }
}
