package cart.cartitem.web;

import cart.authenticate.AuthUserInformation;
import cart.authenticate.UserPrincipal;
import cart.cartitem.application.GetCartItemInformationService;
import cart.cartitem.application.dto.CartItemInformation;
import cart.cartitem.web.response.CartPageCartItemResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/cart")
@Controller
public class CartPageController {

    private final GetCartItemInformationService getCartItemInformationService;

    public CartPageController(GetCartItemInformationService getCartItemInformationService) {
        this.getCartItemInformationService = getCartItemInformationService;
    }

    @GetMapping
    public String cart(Model model, @UserPrincipal AuthUserInformation authUserInfomation) {
        List<CartItemInformation> cartItemInformations = getCartItemInformationService.getCartItemInformations(authUserInfomation.getId());
        List<CartPageCartItemResponse> responses = CartPageCartItemResponse.ofCartItemInformations(cartItemInformations);
        model.addAttribute("cartItems", responses);
        return "cart.html";
    }
}
