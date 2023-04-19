package cart.init;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cart.domain.Cart;
import cart.domain.Member;
import cart.domain.Product;
import cart.repository.CartRepository;
import cart.repository.MemberRepository;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
        private final ProductRepository productRepository;
        private final MemberRepository memberRepository;
        private final CartRepository cartRepository;

        @PostConstruct
        public void init() {
                Product chcken = new Product("chcken",
                                "https://cdn.pixabay.com/photo/2015/03/11/00/31/chicken-667935_960_720.jpg", 22000);
                productRepository.save(chcken);

                Product salad = new Product("salad",
                                "https://cdn.pixabay.com/photo/2016/09/15/19/24/salad-1672505_960_720.jpg",
                                9000);
                productRepository.save(salad);

                Product pizza = new Product("pizza",
                                "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg",
                                29000);
                productRepository.save(pizza);

                Member david = new Member("david@kakaopaysec.com", "password1");
                memberRepository.save(david);

                Member daniel = new Member("daniel@kakaopaysec.com", "password2");
                memberRepository.save(daniel);

                Cart danielCart = Cart.builder().product(pizza).member(daniel).build();
                cartRepository.save(danielCart);

                Cart davidlCart = Cart.builder().product(salad).member(david).build();
                cartRepository.save(davidlCart);
        }
}
