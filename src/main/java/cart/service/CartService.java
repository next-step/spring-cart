package cart.service;

import cart.auth.AuthData;
import cart.controller.response.CartResponse;
import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import cart.repository.CartRepository;
import cart.repository.MemberRepository;
import cart.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public void save(AuthData authData, Long productId) {
        Member member = memberRepository.find(authData.getEmail(), authData.getPassword());
        Product product = productRepository.findById(productId);
        cartRepository.save(new Cart(member.getId(), product.getId()));
    }

    public List<CartResponse> findAll(AuthData authData) {
        Member member = memberRepository.find(authData.getEmail(), authData.getPassword());

        List<Cart> carts = cartRepository.findAll(member.getId());
        return carts.stream().map(cart -> {
            Product product = productRepository.findById(cart.getProductId());
            return CartResponse.extract(cart.getId(), product);
        }).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
