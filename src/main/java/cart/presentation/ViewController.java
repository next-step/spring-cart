package cart.presentation;

import cart.business.AdminService;
import cart.presentation.dto.ViewProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final AdminService adminService;

    public ViewController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String mainController(Model model) {
        List<ViewProduct> viewProducts = adminService.getViewProducts();
        model.addAttribute("products", viewProducts);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<ViewProduct> viewProducts = adminService.getViewProducts();
        model.addAttribute("products", viewProducts);
        return "admin";
    }
}
