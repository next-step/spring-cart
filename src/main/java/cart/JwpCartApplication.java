package cart;

import cart.domain.member.MemberRepository;
import cart.domain.member.model.MemberModel;
import cart.domain.product.ProductRepository;
import cart.domain.product.model.ProductModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JwpCartApplication {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public JwpCartApplication(ProductRepository productRepository, MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JwpCartApplication.class, args);
    }

    @PostConstruct
    public void mockData() {
        productRepository.save(new ProductModel("mock1", "mockUrl1", 10000L));
        productRepository.save(new ProductModel("mock2", "mockUrl2", 20000L));
        productRepository.save(new ProductModel("mock3", "mockUrl3", 30000L));

        memberRepository.save(new MemberModel("helloworld@google.com", "password12"));
        memberRepository.save(new MemberModel("helloworld2@google.com", "password124"));
    }
}
