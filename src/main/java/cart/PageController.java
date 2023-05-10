package cart;

import cart.member.application.MemberService;
import cart.product.application.ProductService;
import cart.member.controller.dto.MembersResponse;
import cart.product.controller.dto.ProductsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ProductService productService;
    private final MemberService memberService;

    public PageController(ProductService productService,
                          MemberService memberService) {
        this.productService = productService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(Model model) {
        ProductsResponse products = productService.getAllProducts();
        model.addAttribute("products", products.getProducts());

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        ProductsResponse products = productService.getAllProducts();
        model.addAttribute("products", products.getProducts());

        return "admin";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        MembersResponse members = memberService.findAllMembers();
        model.addAttribute("members", members.getMembers());

        return "settings";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        // TODO step2에서 진행

        return "cart";
    }

}
