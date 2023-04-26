package cart.domain.cartitem;

import cart.domain.cartitem.model.CartItemModel;
import cart.domain.product.ProductRepository;
import cart.domain.product.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public void createCartItem(String memberEmail, Long productId) {
        cartItemRepository.createNewCart(memberEmail, productId);
    }

    public void removeCartItem(String memberEmail, Long cartItemId) {
        CartItemModel cartItemModel = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 cartItem 입니다."));

        if (!cartItemModel.ownsBy(memberEmail)) {
            throw new IllegalArgumentException("해당 유저의 cartItem 이 아닙니다.");
        }

        cartItemRepository.removeProducts(cartItemId);
    }

    public Map<Long, List<ProductModel>> readMemberCartProducts(String memberEmail) {
        List<CartItemModel> cartItemModels = cartItemRepository.findByMemberEmail(memberEmail);
        List<Long> productIds = cartItemModels.stream()
            .map(CartItemModel::getProductId)
            .collect(Collectors.toList());

        Map<Long, ProductModel> productsByProductId = productRepository.findByIds(productIds);

        return cartItemModels.stream()
            .collect(Collectors.groupingBy(
                CartItemModel::getId,
                Collectors.mapping(a -> productsByProductId.get(a.getProductId()), Collectors.toList())
            ));
    }
}
