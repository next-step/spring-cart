package cart.presentation;

import cart.business.AdminService;
import cart.presentation.dto.RequestProduct;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public void createProduct(@RequestBody RequestProduct product) {
        adminService.createProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody RequestProduct product) {
        adminService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        adminService.deleteProduct(id);
    }
}
