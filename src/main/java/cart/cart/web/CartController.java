package cart.cart.web;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.service.CartService;
import cart.cart.util.AuthInfo;
import cart.cart.util.BasicAuthConverter;
import cart.cart.web.dto.CreateAuthRequest;
import cart.cart.web.dto.CreateCart;
import cart.cart.web.dto.ReadMemberCarts;
import cart.member.domain.dto.MemberDto;
import cart.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;

    @GetMapping(value = "/carts/auth")
    public ReadMemberCarts.Response readMemberCartsInAuth(
            HttpServletRequest request) throws AuthException {
        MemberDto memberDto = getMemberFromAuth(request);
        return readMemberCarts(memberDto.getId());
    }

    private MemberDto getMemberFromAuth(HttpServletRequest request) throws AuthException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            throw new AuthException("인증 정보가 없습니다");
        }
        AuthInfo authInfo = BasicAuthConverter.convert(authorization);
        return memberService.getMember(authInfo.getUsername(), authInfo.getPassword());
    }

    @GetMapping("/carts")
    public ReadMemberCarts.Response readMemberCarts(@RequestParam Long memberId) {
        return ReadMemberCarts.Response.of(
                memberId, cartService.getCartsByMemberId(memberId));
    }

    @PostMapping("/carts/auth")
    public CreateCart.Response createCart(@RequestBody CreateAuthRequest request,
                                          HttpServletRequest httpServletRequest) throws AuthException {
        MemberDto memberDto = getMemberFromAuth(httpServletRequest);
        return CreateCart.Response.of(
                cartService.addCart(memberDto.getId(), request.getProductId()));
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
