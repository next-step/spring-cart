package cart.controller;

import cart.domain.Member;
import cart.domain.Members;
import cart.domain.Product;
import cart.domain.Products;
import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final Members members = new Members();

    {
        members.add(new Member("hi", "ewr"));
        members.add(new Member("bye", "ewr"));
    }

    @GetMapping("/settings")
    public String index(Model model) {
        model.addAttribute("members", members.getAll());
        return "settings.html";
    }
}
