package cart.controller;

import cart.domain.Members;
import cart.domain.Products;
import cart.dto.MemberResponse;
import cart.dto.ProductResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final Products products;
    private final Members members;

    public ViewController(Products products, Members members) {
        this.products = products;
        this.members = members;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", ProductResponse.listOf(products.getAll()));
        return "index.html";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", ProductResponse.listOf(products.getAll()));
        return "admin.html";
    }

    @GetMapping("/settings")
    public String setting(Model model) {
        model.addAttribute("members", MemberResponse.listOf(members.getAll()));
        return "settings.html";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart.html";
    }
}
