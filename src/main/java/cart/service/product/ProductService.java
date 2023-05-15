package cart.service.product;

import cart.domain.cart.Cart;
import cart.domain.product.Product;
import cart.infrastructure.dao.CartDao;
import cart.infrastructure.dao.ProductDao;
import cart.web.product.dto.ProductResponseDto;
import cart.web.product.dto.ProductSaveRequestDto;
import cart.web.product.dto.ProductUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductDao productDao;
    private final CartDao cartDao;

    public List<ProductResponseDto> findAll() {
        return productDao.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(ProductSaveRequestDto requestDto) {
        return productDao.insert(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + id));

        product.update(requestDto.getName(), requestDto.getImageUrl(), requestDto.getPrice());
        return productDao.update(product);
    }

    @Transactional
    public Long delete(Long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + id));

        deleteCarts(product);

        return productDao.delete(product);
    }

    private void deleteCarts(Product product) {
        List<Long> cartIds = cartDao.findAllByProductId(product.getId()).stream()
                .map(Cart::getId)
                .collect(Collectors.toList());

        cartDao.batchDelete(cartIds);
    }

}
