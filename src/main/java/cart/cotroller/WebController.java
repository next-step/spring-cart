package cart.cotroller;

import cart.model.Product;
import cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebController  {
    @Autowired
    ProductService productService;

    @RequestMapping("/")
    String getProductList(Model model) {
        List<Product> resultProductList = productService.getProductList();
        model.addAttribute("products", resultProductList);

        return "index";
    }

    @RequestMapping("/admin")
    String admin(Model model) {
        List<Product> resultProductList = productService.getProductList();
        model.addAttribute("products", resultProductList);

        return "admin";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "cart.html";
    }


    @RequestMapping("settings")
    public String setting(){
        return "settings.html";
    }
}
