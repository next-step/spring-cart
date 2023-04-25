package cart.cartitem.web;

import cart.authenticate.AuthUserInformation;
import cart.authenticate.UserPrincipal;
import cart.cartitem.application.dto.CartItemAddData;
import cart.cartitem.application.dto.CartItemInformation;
import cart.cartitem.application.usecase.CartItemAddUseCase;
import cart.cartitem.application.usecase.GetCartItemInformationUseCase;
import cart.cartitem.web.request.CartItemAddRequest;
import cart.cartitem.web.response.CartItemInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart/items")
@RestController
public class CartItemManageController {

    private final CartItemAddUseCase cartItemAddUseCase;
    private final GetCartItemInformationUseCase getCartItemInformationUseCase;

    public CartItemManageController(CartItemAddUseCase cartItemAddUseCase, GetCartItemInformationUseCase getCartItemInformationUseCase) {
        this.cartItemAddUseCase = cartItemAddUseCase;
        this.getCartItemInformationUseCase = getCartItemInformationUseCase;
    }

    @PostMapping
    public ResponseEntity<CartItemInformationResponse> addCartItem(
            @RequestBody CartItemAddRequest request,
            @UserPrincipal AuthUserInformation authUserInformation
    ) {
        CartItemAddData cartItemAddData = request.toCartItemAddDataWithUserId(authUserInformation.getId());
        CartItemInformation cartItemInformation = cartItemAddUseCase.addCartItem(cartItemAddData);
        CartItemInformationResponse response = CartItemInformationResponse.fromCartItemInformation(cartItemInformation);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CartItemInformationResponse>> getCartItems(@UserPrincipal AuthUserInformation authUserInformation) {
        List<CartItemInformation> cartItemInformations = getCartItemInformationUseCase.getCartItemInformations(authUserInformation.getId());
        List<CartItemInformationResponse> responses = CartItemInformationResponse.ofCartItemInformations(cartItemInformations);
        return ResponseEntity.ok(responses);
    }
}
