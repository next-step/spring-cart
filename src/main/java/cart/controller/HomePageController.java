package cart.controller;

import cart.controller.response.HomePageProductResponse;
import cart.dto.HomePageProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/")
@Controller
public class HomePageController {

    private final HomePageProductsUseCase homePageProductsUseCase;

    public HomePageController(HomePageProductsUseCase homePageProductsUseCase) {
        this.homePageProductsUseCase = homePageProductsUseCase;
    }

    @GetMapping
    public String home(Model model) {
        List<HomePageProduct> homePageProducts = homePageProductsUseCase.getHomePageProducts();
        List<HomePageProductResponse> homePageProductResponses = HomePageProductResponse.ofHomePageProducts(homePageProducts);
        model.addAttribute("products", homePageProductResponses);
        return "index.html";
    }
}
