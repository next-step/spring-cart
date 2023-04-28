package cart.user.web;

import cart.user.application.dto.UserInformation;
import cart.user.application.usecase.GetUserInformationUseCase;
import cart.user.web.response.SettingPageMemberResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/settings")
@Controller
public class SettingPageController {
    private final GetUserInformationUseCase getUserInformationUseCase;

    public SettingPageController(GetUserInformationUseCase getUserInformationUseCase) {
        this.getUserInformationUseCase = getUserInformationUseCase;
    }

    @GetMapping
    public String settings(Model model) {
        List<UserInformation> userInformations = getUserInformationUseCase.getUserInformations();
        List<SettingPageMemberResponse> responses = SettingPageMemberResponse.ofUserInformations(userInformations);
        model.addAttribute("members", responses);
        return "settings.html";
    }
}
