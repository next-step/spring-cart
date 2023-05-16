package cart.presentation;

import cart.application.ProductService;
import cart.application.dto.FindProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

  private final ProductService productService;

  @GetMapping("/")
  public String getProductListPage(Model model) {
    List<FindProductResponse> products = this.productService.getAllProducts();
    model.addAttribute("products", products);

    return "index";
  }
}
