package cart.user.controller;

import cart.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class MemberController {
    private final ModelAndView modelAndView = new ModelAndView();
    private final MemberService memberService;

    @GetMapping("/settings")
    public ModelAndView settingsIndex(Model model) {
        model.addAttribute("members", memberService.findAll());
        modelAndView.setViewName("settings");
        return modelAndView;
    }
}
