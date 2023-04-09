package cart.admin.api;

import cart.admin.application.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductController {

    private final AdminService adminService;

    public ProductController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String getAllProducts(Model model){

        model.addAttribute("products", adminService.selectAllProduct());

        return "index";
    }

}
