package cart.admin.controller;

import cart.product.dto.request.ProductRequest;
import cart.product.dto.response.ProductResponse;
import cart.product.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/admin")
@RestController
public class AdminController {
    private final ProductService productService;
    private final ModelAndView modelAndView = new ModelAndView();

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/product")
    public void insertProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.insert(productRequest);
    }

    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }


}
