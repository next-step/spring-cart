package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cart.domain.Cart;
import cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> getList() {
        return cartRepository.findAll();
    }
}
