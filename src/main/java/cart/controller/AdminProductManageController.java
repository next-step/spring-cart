package cart.controller;

import cart.controller.request.AdminProductInformationResponse;
import cart.controller.request.AdminProductRegistrationRequest;
import cart.controller.usecase.ProductRegistrationUseCase;
import cart.dto.ProductInformation;
import cart.dto.ProductRegistrationData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/products")
@RestController
public class AdminProductManageController {

    private final ProductRegistrationUseCase productRegistrationUseCase;

    public AdminProductManageController(ProductRegistrationUseCase productRegistrationUseCase) {
        this.productRegistrationUseCase = productRegistrationUseCase;
    }

    @PostMapping
    public ResponseEntity<AdminProductInformationResponse> registerProduct(@RequestBody AdminProductRegistrationRequest request) {
        ProductRegistrationData productRegistrationData = request.toProductRegistrationData();
        ProductInformation productInformation = productRegistrationUseCase.registerProduct(productRegistrationData);
        AdminProductInformationResponse response = AdminProductInformationResponse.fromProductInformation(productInformation);
        return ResponseEntity.ok(response);
    }
}
