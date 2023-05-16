package cart.presentation;

import cart.business.ItemService;
import cart.infra.AuthInfo;
import cart.infra.AuthorizationExtractor;
import cart.infra.BasicAuthorizationExtractor;
import cart.presentation.dto.RequestAddItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/items")
@RestController
public class ItemController {

    private final AuthorizationExtractor<AuthInfo> auth = new BasicAuthorizationExtractor();
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity getItems(HttpServletRequest request) {
        Optional<AuthInfo> optionalAuthInfo = auth.extract(request);

        if (optionalAuthInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        AuthInfo authInfo = optionalAuthInfo.get();
        return ResponseEntity.ok(itemService.getCartItems(authInfo));
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(HttpServletRequest request, @RequestBody RequestAddItem addItem) {
        Optional<AuthInfo> optionalAuthInfo = auth.extract(request);

        if (optionalAuthInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        itemService.addToCart(optionalAuthInfo.get(), addItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCartItem(@PathVariable Long id, HttpServletRequest request) {
        Optional<AuthInfo> optionalAuthInfo = auth.extract(request);

        if (optionalAuthInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        itemService.deleteCartItem(id);
        return ResponseEntity.ok().build();
    }
}
