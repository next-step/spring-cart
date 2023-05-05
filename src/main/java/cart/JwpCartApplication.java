package cart;

import cart.domain.MemberEntity;
import cart.domain.ProductEntity;
import cart.repository.MemberRepository;
import cart.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JwpCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwpCartApplication.class, args);
    }

}

@Component
class DummyDataInitializer implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public DummyDataInitializer(ProductRepository productRepository, MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        memberRepository.save(new MemberEntity("test1@test.com", "1234"));
        memberRepository.save(new MemberEntity("test2@test.com", "1234"));
        memberRepository.save(new MemberEntity("test3@test.com", "1234"));

        productRepository.save(new ProductEntity(
                "테스트 상품1",
                "https://barunchicken.com/wp-content/uploads/2022/07/%EA%B3%A8%EB%93%9C%EC%B9%98%ED%82%A8-2-1076x807.jpg",
                10_000L)
        );
        productRepository.save(new ProductEntity(
                "테스트 상품2",
                "https://barunchicken.com/wp-content/uploads/2022/07/%EA%B3%A8%EB%93%9C%EC%B9%98%ED%82%A8-2-1076x807.jpg",
                10_000L)
        );
        productRepository.save(new ProductEntity(
                "테스트 상품3",
                "https://barunchicken.com/wp-content/uploads/2022/07/%EA%B3%A8%EB%93%9C%EC%B9%98%ED%82%A8-2-1076x807.jpg",
                10_000L)
        );
    }
}
