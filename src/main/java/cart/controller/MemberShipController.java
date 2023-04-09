package cart.controller;

import cart.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberShipController {

    private MemberService memberService;

    public MemberShipController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("members", memberService.showMember());
        return "settings";
    }

}
