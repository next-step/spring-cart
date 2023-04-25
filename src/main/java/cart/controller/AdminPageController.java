package cart.controller;

import cart.controller.response.AdminPageProductResponse;
import cart.controller.usecase.GetProductInformationUseCase;
import cart.dto.ProductInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminPageController {

    private final GetProductInformationUseCase getProductInformationUseCase;

    public AdminPageController(GetProductInformationUseCase getProductInformationUseCase) {
        this.getProductInformationUseCase = getProductInformationUseCase;
    }

    @GetMapping
    public String admin(Model model) {
        List<ProductInformation> productInformations = getProductInformationUseCase.getProductInformations();
        List<AdminPageProductResponse> adminPageProductResponses = AdminPageProductResponse.ofProductInformations(productInformations);
        model.addAttribute("products", adminPageProductResponses);
        return "admin.html";
    }
}
