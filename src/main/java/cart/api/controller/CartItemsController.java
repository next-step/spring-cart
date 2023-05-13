package cart.api.controller;

import cart.domain.entity.CartItem;
import cart.domain.entity.Member;
import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import cart.domain.service.AuthService;
import cart.domain.service.CartItemService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart/items")
public class CartItemsController {
    private final AuthService authService;
    private final CartItemService cartItemService;
    private final ProductRepository productRepository;

    public CartItemsController(AuthService authService, CartItemService cartItemService, ProductRepository productRepository) {
        this.authService = authService;
        this.cartItemService = cartItemService;
        this.productRepository = productRepository;
    }
    
    @GetMapping
    public List<Product> getCartProducts(Model model, @RequestHeader String authorization) {
        Member member = authService.validateAndLogin(authorization);
        return cartItemService.getAllByMember(member.getId())
                .stream()
                .map(CartItem::getProductId)
                .map(productRepository::findById)
                .collect(Collectors.toList());
    }

    @PostMapping("{productId}")
    public void addCart(Model model, @RequestHeader String authorization, @PathVariable Long productId) {
        Member member = authService.validateAndLogin(authorization);
        cartItemService.add(new CartItem(member.getId(), productId));
    }

    @DeleteMapping("{productId}")
    public void remove(Model model, @RequestHeader String authorization, @PathVariable Long productId) {
        Member member = authService.validateAndLogin(authorization);
        cartItemService.delete(new CartItem(member.getId(), productId));
    }
}
