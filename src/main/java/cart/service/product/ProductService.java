package cart.service.product;

import cart.domain.product.Product;
import cart.infrastructure.dao.ProductDao;
import cart.web.product.dto.ProductResponseDto;
import cart.web.product.dto.ProductSaveRequestDto;
import cart.web.product.dto.ProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDao productDao;

    public List<ProductResponseDto> findAll() {
        return productDao.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long save(ProductSaveRequestDto requestDto) {
        return productDao.insert(requestDto.toEntity()).getId();
    }

    public Long update(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + id));

        product.update(requestDto.getName(), requestDto.getImageUrl(), requestDto.getPrice());
        return productDao.update(product);
    }

    public Long delete(Long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + id));

        return productDao.delete(product);
    }

}
