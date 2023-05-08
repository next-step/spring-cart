package cart.domain.admin;

import cart.domain.global.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final List<Product> products;

    @Autowired
    public AdminController(List<Product> products){
        this.products = products;
    }

    @GetMapping("")
    public String get(Model model){
        model.addAttribute("products", products);
        return "admin";
    }

    @PostMapping("/product")
    public String post(
            @RequestBody Product product
    ){
        products.add(new Product(product.getName(), product.getImage(), product.getPrice()));
        return "admin";
    }

    @PutMapping("/product/{id}")
    public String put(@RequestBody Product product){
        products
                .stream()
                .filter(pro -> pro.getId() == product.getId())
                .findFirst()
                .ifPresent(pro -> {
                    pro.setName(product.getName());
                    pro.setImage(product.getImage());
                    pro.setPrice(product.getPrice());
                });
        return "admin";
    }

    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable Integer id){
        products.removeIf(pro -> pro.getId() == id);
        return "admin";
    }
}
