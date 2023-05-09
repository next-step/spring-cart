package cart.admin.controller;

import cart.product.dto.request.ProductRequest;
import cart.product.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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

    @PostMapping("/create-product")
    public void insertProduct(Model model, @RequestBody ProductRequest productRequest) {
        productService.insert(productRequest);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
    }

    @PutMapping("/update-product/{id}")
    public void updateProduct(Model model, @PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(Model model, @PathVariable Long id) {
        productService.delete(id);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
    }


}
