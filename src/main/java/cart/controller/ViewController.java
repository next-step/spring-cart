package cart.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

}
