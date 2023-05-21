package cart.item.service;

import cart.enums.exceptions.ErrorCode;
import cart.exceptions.ProductNotFoundException;
import cart.item.dto.request.ItemAddRequest;
import cart.item.dto.request.ItemDeleteRequest;
import cart.item.dto.response.ItemResponse;
import cart.item.repository.ItemDao;
import cart.product.domain.Product;
import cart.product.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemDao itemDao;
    private final ProductDao productDao;

    public List<ItemResponse> findAllByMemberId(Long id) {
        return itemDao.findAllByMemberId(id).stream().map(item -> {
            Product product = productDao.findById(item.getProductId()).orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND_PRODUCT));
            return new ItemResponse(item.getId(), product.getName(), product.getImg(), product.getPrice());
        }).collect(Collectors.toList());
    }

    public void addCarItem(ItemAddRequest itemAddRequest) {
        Product product = productDao.findById(itemAddRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND_PRODUCT));
        itemDao.insert(itemAddRequest.setProductId(product.getId()));
    }

    public void deleteCarItem(ItemDeleteRequest itemDeleteRequest) {
        itemDao.delete(itemDeleteRequest);
    }
}
