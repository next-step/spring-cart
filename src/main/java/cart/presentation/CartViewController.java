package cart.presentation;

import cart.application.ProductService;
import cart.application.dto.FindProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartViewController {

  private final ProductService productService;

  @GetMapping("/")
  public String getProductListPage(Model model) {
    List<FindProductResponse> products = this.productService.getAllProducts();
    model.addAttribute("products", products);

    return "index";
  }

  @GetMapping("/admin")
  public String getAdminPage(Model model) {
    List<FindProductResponse> products = this.productService.getAllProducts();
    model.addAttribute("products", products);
    return "admin";
  }
}
