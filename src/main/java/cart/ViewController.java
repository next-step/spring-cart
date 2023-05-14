package cart;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
