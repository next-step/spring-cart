package cart.product.api;

import cart.auth.dto.AuthInfo;
import cart.auth.infrastructure.BasicAuthorizationExtractor;
import cart.product.application.ProductCartService;
import cart.product.dto.ProductCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class ProductCartController {

    private final ProductCartService productCartService;

    private final BasicAuthorizationExtractor basicAuthorizationExtractor;

    public ProductCartController(ProductCartService productCartService, BasicAuthorizationExtractor basicAuthorizationExtractor) {
        this.productCartService = productCartService;
        this.basicAuthorizationExtractor = basicAuthorizationExtractor;
    }

    @GetMapping
    public String getAllCarts(Model model) {
        return "cart";
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductCartDto>> getAllCarts(HttpServletRequest request) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);

        return ResponseEntity.ok(productCartService.getCarts(authInfo));
    }

    @PostMapping("/{productId}")
    public ResponseEntity addCarts(HttpServletRequest request, @PathVariable Integer productId) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);

        productCartService.saveCart(authInfo, productId);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCarts(HttpServletRequest request, @PathVariable Integer cartId) {
        AuthInfo authInfo = basicAuthorizationExtractor.extract(request);

        productCartService.deleteCart(cartId);

        return ResponseEntity.accepted().build();
    }


}
