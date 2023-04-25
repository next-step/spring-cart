package cart.controller;

import cart.controller.request.AdminProductInformationResponse;
import cart.controller.request.AdminProductRegistrationRequest;
import cart.controller.request.AdminProductUpdateRequest;
import cart.controller.usecase.ProductDeleteUseCase;
import cart.controller.usecase.ProductRegistrationUseCase;
import cart.controller.usecase.ProductUpdateUseCase;
import cart.dto.ProductInformation;
import cart.dto.ProductRegistrationData;
import cart.dto.ProductUpdateData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/products")
@RestController
public class AdminProductManageController {

    private final ProductRegistrationUseCase productRegistrationUseCase;
    private final ProductUpdateUseCase productUpdateUseCase;
    private final ProductDeleteUseCase productDeleteUseCase;

    public AdminProductManageController(
            ProductRegistrationUseCase productRegistrationUseCase,
            ProductUpdateUseCase productUpdateUseCase,
            ProductDeleteUseCase productDeleteUseCase
    ) {
        this.productRegistrationUseCase = productRegistrationUseCase;
        this.productUpdateUseCase = productUpdateUseCase;
        this.productDeleteUseCase = productDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AdminProductInformationResponse> registerProduct(@RequestBody AdminProductRegistrationRequest request) {
        ProductRegistrationData productRegistrationData = request.toProductRegistrationData();
        ProductInformation productInformation = productRegistrationUseCase.registerProduct(productRegistrationData);
        AdminProductInformationResponse response = AdminProductInformationResponse.fromProductInformation(productInformation);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<AdminProductInformationResponse> updateProduct(@PathVariable Long productId, @RequestBody AdminProductUpdateRequest request) {
        ProductUpdateData productUpdateData = request.toProductUpdateData();
        ProductInformation productInformation = productUpdateUseCase.updateProduct(productId, productUpdateData);
        AdminProductInformationResponse response = AdminProductInformationResponse.fromProductInformation(productInformation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productDeleteUseCase.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
