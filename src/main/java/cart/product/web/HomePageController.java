package cart.product.web;

import cart.product.web.response.HomePageProductResponse;
import cart.product.application.usecase.GetProductInformationUseCase;
import cart.product.application.dto.ProductInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/")
@Controller
public class HomePageController {

    private final GetProductInformationUseCase getProductInformationUseCase;

    public HomePageController(GetProductInformationUseCase getProductInformationUseCase) {
        this.getProductInformationUseCase = getProductInformationUseCase;
    }

    @GetMapping
    public String home(Model model) {
        List<ProductInformation> productInformations = getProductInformationUseCase.getProductInformations();
        List<HomePageProductResponse> homePageProductResponses = HomePageProductResponse.ofProductInformations(productInformations);
        model.addAttribute("products", homePageProductResponses);
        return "index.html";
    }
}
