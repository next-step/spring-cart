package cart.product.web;

import cart.product.web.response.AdminProductInformationResponse;
import cart.product.web.request.AdminProductRegistrationRequest;
import cart.product.web.request.AdminProductUpdateRequest;
import cart.product.application.usecase.ProductDeleteUseCase;
import cart.product.application.usecase.ProductRegistrationUseCase;
import cart.product.application.usecase.ProductUpdateUseCase;
import cart.product.application.dto.ProductInformation;
import cart.product.application.dto.ProductRegistrationData;
import cart.product.application.dto.ProductUpdateData;
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
