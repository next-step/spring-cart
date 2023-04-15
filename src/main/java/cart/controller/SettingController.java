package cart.controller;

import cart.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SettingController {
    @RequestMapping("/settings")
    public String settings(Model model) {
        List<Member> members = List.of(
                new Member("testemail1", "password"),
                new Member("testemail2", "password")
        );
        model.addAttribute("members", members);
        return "settings";
    }
}
