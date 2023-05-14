package cart.cart.web;

import cart.cart.domain.service.CartService;
import cart.auth.AuthInfo;
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

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;


@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;

    @GetMapping(value = "/carts/auth")
    public ReadMemberCarts.Response readMemberCartsInAuth(
            HttpServletRequest request) throws AuthenticationException {
        MemberDto memberDto = getMemberFromAuth(request);
        return readMemberCarts(memberDto.getId());
    }

    private MemberDto getMemberFromAuth(HttpServletRequest request) throws AuthenticationException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            throw new AuthenticationException("인증 정보가 없습니다");
        }
        AuthInfo authInfo = convert(authorization);
        return memberService.getMember(authInfo.getUsername(), authInfo.getPassword());
    }

    private AuthInfo convert(String basicAuthLiteral) {
        String authLiteral = basicAuthLiteral.replace("Basic ", "");
        String decodedString
                = new String(Base64.getDecoder().decode(authLiteral.getBytes()));

        String[] authArray = decodedString.split(":");
        return new AuthInfo(authArray[0], authArray[1]);
    }


    @GetMapping("/carts")
    public ReadMemberCarts.Response readMemberCarts(@RequestParam Long memberId) {
        return ReadMemberCarts.Response.of(
                memberId, cartService.getCartsByMemberId(memberId));
    }

    @PostMapping("/carts/auth")
    public CreateCart.Response createCart(@RequestBody CreateAuthRequest request,
                                          HttpServletRequest httpServletRequest) throws AuthenticationException {
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
