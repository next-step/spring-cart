package cart.admin.api;

import cart.admin.application.AdminService;
import cart.admin.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/products")
    public String selectProducts(Model model) {
        model.addAttribute("products", adminService.selectAllProduct());
        return "admin";
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductDto product) {
        adminService.createProduct(() -> product.toEntity());

        return ResponseEntity.created(URI.create("/admin")).build();
    }

    @PutMapping("/product")
    public ResponseEntity updateProduct(@RequestBody ProductDto product) {
        adminService.updateProduct(() -> product.toEntity());

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {

        adminService.deleteProduct(productId);

        return ResponseEntity.accepted().build();
    }

}
