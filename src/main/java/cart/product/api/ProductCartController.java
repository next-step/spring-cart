package cart.product.api;

import cart.auth.dto.AuthInfo;
import cart.auth.infrastructure.AuthenticationPrincipal;
import cart.auth.infrastructure.BasicAuthorizationExtractor;
import cart.product.application.ProductCartService;
import cart.product.dto.ProductCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class ProductCartController {

    private final ProductCartService productCartService;

    public ProductCartController(ProductCartService productCartService) {
        this.productCartService = productCartService;
    }

    @GetMapping
    public String getAllCarts(Model model) {
        return "cart";
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductCartDto>> getAllCarts(@AuthenticationPrincipal AuthInfo authInfo) {
        return ResponseEntity.ok(productCartService.getCarts(authInfo));
    }

    @PostMapping("/{productId}")
    public ResponseEntity addCarts(@AuthenticationPrincipal AuthInfo authInfo, @PathVariable Integer productId) {
        productCartService.saveCart(authInfo, productId);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCarts(@AuthenticationPrincipal AuthInfo authInfo, @PathVariable Integer cartId) {
        productCartService.deleteCart(cartId);

        return ResponseEntity.accepted().build();
    }


}
