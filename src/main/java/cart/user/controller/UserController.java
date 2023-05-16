package cart.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/user")
@RestController
public class UserController {
    private final ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/settings")
    public ModelAndView settingsIndex() {
        modelAndView.setViewName("settings");
        return modelAndView;
    }
}
