package cart.presentation.view.settings;

import cart.domain.member.MemberService;
import cart.presentation.view.settings.response.SettingsMemberResponse;
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
    public String settings(Model model) {
        model.addAttribute("members", SettingsMemberResponse.list(memberService.memberList()));
        return "settings";
    }
}
