package cart.controller;


import cart.Service.CartService;
import cart.Service.MemberService;
import cart.Service.ProductService;
import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public String cart(Model model) {
        return "cart";
    }
    @PostMapping("/add")
    public ResponseEntity<Integer> add(@RequestBody Cart cart, HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        System.out.println("cartatatatat"+cart.getProductId());
        cart.setEmail(email);

        return ResponseEntity.ok(cartService.addCart(cart));
    }

}