package cart.admin.api;

import cart.admin.application.AdminService;
import cart.admin.domain.Product;
import cart.admin.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/admin/product/")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminProductList(Model model) {
        model.addAttribute("products", adminService.selectAllProduct());
        return "admin";
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDto product) {
        adminService.createProduct(product);

        return ResponseEntity.created(URI.create("/admin")).build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductDto product) {
        adminService.updateProduct(product);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {

        adminService.deleteProduct(productId);

        return ResponseEntity.accepted().build();
    }

}
