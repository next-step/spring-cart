package cart.controller;

import cart.controller.response.HomePageProductResponse;
import cart.controller.usecase.GetProductDtoUseCase;
import cart.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/")
@Controller
public class HomePageController {

    private final GetProductDtoUseCase getProductDtoUseCase;

    public HomePageController(GetProductDtoUseCase getProductDtoUseCase) {
        this.getProductDtoUseCase = getProductDtoUseCase;
    }

    @GetMapping
    public String home(Model model) {
        List<ProductDto> productDtos = getProductDtoUseCase.getProductDtos();
        List<HomePageProductResponse> homePageProductResponses = HomePageProductResponse.ofProductDtos(productDtos);
        model.addAttribute("products", homePageProductResponses);
        return "index.html";
    }
}
