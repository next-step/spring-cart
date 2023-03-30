package cart.admin.api;

import cart.admin.application.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String adminProductList(Model model) {
        model.addAttribute("products", adminService.selectAllProduct());
        return "admin";
    }


}
