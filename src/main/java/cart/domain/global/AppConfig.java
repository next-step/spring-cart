package cart.domain.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Product> products() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "치킨", "https://jamaicans.com/wp-content/uploads/Jamaican-Fried-Chicken-Recipe-810x540.jpg", 10000));
        products.add(new Product(2, "샐러드", "http://3.bp.blogspot.com/-SwCJQrPE4HY/T2qH0Cd5WUI/AAAAAAAADm8/Jfo1ZxtSx3o/s1600/random+salad.jpg", 20000));
        products.add(new Product(3, "피자", "https://spuf.org/wp-content/uploads/2020/05/pizza-1202775_1280-SPUF.jpg", 13000));
        return products;
    }
}
