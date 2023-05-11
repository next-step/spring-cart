package cart.cart.domain.service;

import cart.cart.domain.dto.CartDto;
import cart.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartDto> getCartsByMemberId(Long memberId) {
        return cartRepository.findByMemberId(memberId).stream()
                .map(CartDto::from)
                .collect(Collectors.toList());
    }

}
