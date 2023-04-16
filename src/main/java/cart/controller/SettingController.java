package cart.controller;

import cart.controller.response.MemberResponse;
import cart.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SettingController {
    private final MemberService memberService;

    public SettingController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/settings")
    public String settings(Model model) {
        List<MemberResponse> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "settings";
    }
}
