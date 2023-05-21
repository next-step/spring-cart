package cart.controller;

import cart.domain.Product;
import cart.domain.User;
import cart.service.ProductService;
import cart.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

  private final ProductService productService;

  public ViewController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String getAll(Model model) {
    List<Product> products = productService.getAll();
    model.addAttribute("products", products);
    return "index";
  }

  @GetMapping("/cart")
  public String getAll() {
    return "cart";
  }
}
