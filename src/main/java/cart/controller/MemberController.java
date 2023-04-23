package cart.controller;

import cart.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/settings")
    public String getMembers(Model model) {
        model.addAttribute(
                "members",
                memberService.getMembers()
        );
        return "/settings";
    }
}
