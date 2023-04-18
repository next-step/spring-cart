package cart.settings;

import cart.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final MemberService memberService;

    @GetMapping("/settings")
    public String settings(Model model) {

        var members = memberService.findByAll();

        model.addAttribute("members", members);

        return "/settings";
    }
}
