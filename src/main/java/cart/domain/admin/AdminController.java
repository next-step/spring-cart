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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final List<Product> products;

    public AdminController(AdminService adminService, List<Product> products){
        this.adminService = adminService;
        this.products = products;
    }

    @GetMapping("")
    public ModelAndView get(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/product")
    public String post(
            @RequestBody Product product
    ){
        adminService.create(product);
        return "admin";
    }

    @PutMapping("/product/{id}")
    public String put(@RequestBody Product product){
        adminService.update(product);
        return "admin";
    }

    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable Integer id){
        adminService.delete(id);
        return "admin";
    }
}
