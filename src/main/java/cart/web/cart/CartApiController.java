package cart.web.cart;

import cart.domain.user.User;
import cart.service.cart.CartService;
import cart.web.cart.dto.CartAddRequestDto;
import cart.web.cart.dto.CartResponseDto;
import cart.web.user.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@RestController
public class CartApiController {

    private final CartService cartService;

    @GetMapping
    public List<CartResponseDto> findAll(@CurrentUser User user) {
        return cartService.findAll(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long add(@CurrentUser User user, @RequestBody CartAddRequestDto requestDto) {
        return cartService.add(user, requestDto);
    }

    @DeleteMapping("/{cartId}")
    public Long remove(@CurrentUser User user, @PathVariable Long cartId) {
        return cartService.remove(user, cartId);
    }

}
