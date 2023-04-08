package cart.controller;


import cart.domain.Product;
import cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("products", cartService.showProduct());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", cartService.showProduct());
        return "admin";
    }

    @PostMapping("/createProduct")
    public ResponseEntity createProduct(@RequestBody Product product) {
        cartService.createProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity deleteProduct(@RequestBody Product product) {
        cartService.deleteProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/changeProduct")
    public ResponseEntity<Integer> updateProduct(@RequestBody Product product) {
        cartService.updateProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

}
