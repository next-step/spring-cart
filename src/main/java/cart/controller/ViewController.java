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
  private final UserService userService;

  public ViewController(ProductService productService, UserService userService) {
    this.productService = productService;
    this.userService = userService;
  }

  @GetMapping("/")
  public ModelAndView getAll(ModelAndView model) {
    List<Product> products = productService.getAll();

    model.addObject("products", products);
    model.setViewName("index");
    return model;
  }

  @GetMapping("/settings")
  public ModelAndView showSetting(ModelAndView model) {
    List<User> members = userService.getAll();
    model.addObject("members", members);
    model.setViewName("settings");
    return model;
  }
}
