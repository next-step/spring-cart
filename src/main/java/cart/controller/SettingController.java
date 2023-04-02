package cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cart.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SettingController {
    private final MemberService memberService;

    @GetMapping(value = "/settings")
    public String admin(Model model) {
        model.addAttribute("members", memberService.getList());
        return "/settings";
    }
}
