package cart.controller;

import cart.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    private final MemberService memberService;

    public SettingsController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/settings")
    public String admin(Model model) {
        model.addAttribute("members", memberService.memberList().getUsers());
        return "settings";
    }
}
