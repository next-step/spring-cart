package cart.controller;


import cart.Service.MemberService;
import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("")
    public String cart(Model model) {
        return "cart";
    }
    @PostMapping("/add")
    public ResponseEntity<Member> add(@RequestBody Cart cart) {

        System.out.println("cartatatatat"+cart.getProductId());
        return ResponseEntity.ok(new Member("aad","424"));
    }

}