package cart.presentation;

import cart.business.MemberService;
import cart.business.ProductService;
import cart.presentation.dto.ViewProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final ProductService adminService;
    private final MemberService memberService;

    public ViewController(ProductService adminService, MemberService memberService) {
        this.adminService = adminService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String mainController(Model model) {
        List<ViewProduct> viewProducts = adminService.getProducts();
        model.addAttribute("products", viewProducts);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<ViewProduct> viewProducts = adminService.getProducts();
        model.addAttribute("products", viewProducts);
        return "admin";
    }

    @GetMapping("/settings")
    public String settingsPage(Model model) {
        model.addAttribute("members", memberService.getMembers());
        return "settings";

    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }
}
