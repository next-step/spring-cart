package cart.business;

import cart.data.ItemRepository;
import cart.data.MemberRepository;
import cart.data.ProductRepository;
import cart.data.entity.CartItem;
import cart.data.entity.CartMember;
import cart.data.entity.CartProduct;
import cart.infra.AuthInfo;
import cart.presentation.dto.RequestAddItem;
import cart.presentation.dto.ViewCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public ItemService(MemberRepository memberRepository, ProductRepository productRepository,
                       ItemRepository itemRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }

    public void addToCart(AuthInfo authInfo, RequestAddItem addItem) {
        CartMember member = memberRepository.getMember(authInfo.getEmail(), authInfo.getPassword())
                .orElseThrow(() -> new RuntimeException(authInfo + "에 해당하는 맴버가 없습니다."));

        CartProduct product = productRepository.getProductById(addItem.getProductId())
                .orElseThrow(() -> new RuntimeException(addItem.getProductId() + "에 대한 상품이 없습니다."));

        CartItem item = new CartItem(member.getMemberId(), product.getProductId());
        itemRepository.addToCart(item);
    }

    public List<ViewCartItem> getCartItems(AuthInfo authInfo) {
        CartMember member = memberRepository.getMember(authInfo.getEmail(), authInfo.getPassword())
                .orElseThrow(() -> new RuntimeException(authInfo + "에 해당하는 맴버가 없습니다."));
        return itemRepository.getCartItems(member.getMemberId());
    }

    public void deleteCartItem(Long id) {
        itemRepository.removeFromCart(id);
    }
}
