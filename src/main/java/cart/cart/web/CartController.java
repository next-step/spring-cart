package cart.cart.web;

import cart.auth.AuthPrincipal;
import cart.cart.domain.service.CartService;
import cart.cart.web.dto.CreateAuthRequest;
import cart.cart.web.dto.CreateCart;
import cart.cart.web.dto.ReadMemberCarts;
import cart.global.config.argumentResolver.AuthenticationPrincipal;
import cart.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;

    @GetMapping(value = "/carts/auth")
    public ReadMemberCarts.Response readMemberCartsInAuth(@AuthenticationPrincipal AuthPrincipal authPrincipal) {
        return readMemberCarts(authPrincipal.getMemberId());
    }

    @GetMapping("/carts")
    public ReadMemberCarts.Response readMemberCarts(@RequestParam Long memberId) {
        return ReadMemberCarts.Response.of(
                memberId, cartService.getCartsByMemberId(memberId));
    }

    @PostMapping("/carts/auth")
    public CreateCart.Response createCart(@RequestBody CreateAuthRequest request,
                                          @AuthenticationPrincipal AuthPrincipal authPrincipal){
        return CreateCart.Response.of(
                cartService.addCart(authPrincipal.getMemberId(), request.getProductId()));
    }

    @PostMapping("/carts")
    public CreateCart.Response createCart(@RequestBody CreateCart.Request request) {
        return CreateCart.Response.of(
                cartService.addCart(request.getMemberId(), request.getProductId()));
    }

    @DeleteMapping("/carts/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }

    @GetMapping("/cart")
    public ModelAndView showCart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        return modelAndView;
    }
}
