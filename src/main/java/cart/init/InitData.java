package cart.init;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitData {
    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        Product chcken = new Product("chcken",
                "https://cdn.pixabay.com/photo/2015/03/11/00/31/chicken-667935_960_720.jpg", 22000);
        // admin.setRole(Role.ADMIN);
        productRepository.save(chcken);

        Product salad = new Product("salad", "https://cdn.pixabay.com/photo/2016/09/15/19/24/salad-1672505_960_720.jpg",
                9000);
        // hyeonic.setRole(Role.product);
        productRepository.save(salad);

        Product pizza = new Product("pizza", "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg",
                29000);
        // hyeonic.setRole(Role.product);
        productRepository.save(pizza);

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            log.info(product.toString());
        }
    }
}
