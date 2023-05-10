package cart.web;

import cart.service.product.ProductService;
import cart.web.dto.ProductResponseDto;
import cart.web.dto.ProductSaveRequestDto;
import cart.web.dto.ProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long save(@RequestBody ProductSaveRequestDto requestDto) {
        return productService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductUpdateRequestDto requestDto) {
        return productService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return productService.delete(id);
    }

}
