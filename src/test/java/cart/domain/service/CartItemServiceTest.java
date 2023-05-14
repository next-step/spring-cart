package cart.domain.service;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;
import cart.domain.repository.ProductRepository;
import cart.testdouble.InMemoryCartItemRepository;
import cart.testdouble.InMemoryProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CartItemServiceTest {

    private CartItemRepository cartItemRepository = new InMemoryCartItemRepository();
    private ProductRepository productRepository = new InMemoryProductRepository();
    private CartItemService cartItemService = new CartItemService(cartItemRepository, productRepository);

    @Test
    void getAllByMember() {
        CartItem cartItem1 = new CartItem(1L, 1L, 1L);
        CartItem cartItem2 = new CartItem(2L, 2L, 2L);
        CartItem cartItem3 = new CartItem(3L, 1L, 3L);
        cartItemRepository.insert(cartItem1);
        cartItemRepository.insert(cartItem2);
        cartItemRepository.insert(cartItem3);

        Collection<CartItem> cartItemsByMember = cartItemService.getAllByMember(1L);

        assertThat(cartItemsByMember.size()).isEqualTo(2);
        assertThat(cartItemsByMember.stream().allMatch(cartItem -> cartItem.getMemberId().equals(1L))).isTrue();
    }

    @Test
    void add() {
        CartItem cartItem1 = new CartItem(1L, 1L, 1L);

        cartItemService.add(cartItem1);

        Collection<CartItem> cartItems = cartItemRepository.findAllByMemberId(1L);
        assertThat(cartItems.size()).isEqualTo(1);
    }

    @Test
    void delete() {
        CartItem cartItem1 = new CartItem(1L, 1L, 1L);
        CartItem cartItem2 = new CartItem(2L, 1L, 2L);
        CartItem cartItem3 = new CartItem(3L, 1L, 3L);
        cartItemRepository.insert(cartItem1);
        cartItemRepository.insert(cartItem2);
        cartItemRepository.insert(cartItem3);

        cartItemService.delete(cartItem1);

        Collection<CartItem> cartItems = cartItemRepository.findAllByMemberId(1L);
        assertThat(cartItems.size()).isEqualTo(2);
    }

    @DisplayName("없는 상품에 대해 삭제 메서드를 수행하면 예외를 던진다.")
    @Test
    void deleteWithException() {
        CartItem cartItem1 = new CartItem(1L, 1L, 1L);
        cartItemRepository.insert(cartItem1);

        assertThrows(NoSuchElementException.class, () -> {
            cartItemService.delete(new CartItem(1L, 100L));
        });
    }
}
