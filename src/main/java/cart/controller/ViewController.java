package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
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
  public ModelAndView getAll(ModelAndView model) {
    List<Product> products = productService.getAll();

    model.addObject("products", products);
    model.setViewName("index");
    return model;
  }

  @GetMapping("/cart")
  public String getAll() {
    return "cart";
  }
}
