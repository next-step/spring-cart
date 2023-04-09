package cart.controller;


import cart.domain.Product;
import cart.service.ProdunctService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SpringCartController {

    private ProdunctService springCartService;

    public SpringCartController(ProdunctService springCartService) {
        this.springCartService = springCartService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("products", springCartService.showProduct());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", springCartService.showProduct());
        return "admin";
    }

    @PostMapping("/createProduct")
    public ResponseEntity createProduct(@RequestBody Product product) {
        springCartService.createProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/removeProduct")
    public ResponseEntity deleteProduct(@RequestBody Product product) {
        springCartService.deleteProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/changeProduct")
    public ResponseEntity<Integer> updateProduct(@RequestBody Product product) {
        springCartService.updateProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

}
