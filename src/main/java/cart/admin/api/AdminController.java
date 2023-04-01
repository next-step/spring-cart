package cart.admin.api;

import cart.admin.application.AdminService;
import cart.admin.domain.Product;
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

    @GetMapping("/select")
    public String adminProductList(Model model) {
        model.addAttribute("products", adminService.selectAllProduct());
        return "admin";
    }

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody Product product){
        adminService.createProduct(product);

        return ResponseEntity.created(URI.create("/admin")).build();
    }

    @PostMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product product){
        adminService.updateProduct(product);

        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/delete")
    public ResponseEntity deleteProduct(@RequestBody Product product){

        adminService.deleteProduct(product.getId());

        return ResponseEntity.accepted().build();
    }

}
