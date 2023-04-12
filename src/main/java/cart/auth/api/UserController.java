package cart.auth.api;

import cart.admin.dto.ProductDto;
import cart.auth.application.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/settings")
    public String getAllUser(Model model){
        model.addAttribute("members", userService.getAllUsers());

        return "settings";
    }

}
