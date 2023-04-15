package cart.repository;

import cart.domain.Product;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProductRepositoryTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll() {
        List<Product> allProduct = productRepository.findAll();

        List<Product> expectList = List.of(
                new Product(1, "단짠단짠 피자", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000),
                new Product(2, "BBQ 3만원치킨", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU", 30000),
                new Product(3, "1번가 떡볶이", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcc4HmNF6vetLFAWsBWluAHmCdeDs74ZmsFw&usqp=CAU", 12200),
                new Product(4, "PC방 라면", "https://health.chosun.com/site/data/img_dir/2020/09/07/2020090702900_0.jpg", 7700));

        Assertions.assertThat(allProduct).containsExactly(expectList.toArray(Product[]::new));
    }

    @Test
    public void update() {

        Product pizza = new Product(1, "test", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000);
        productRepository.update(pizza);

        List<Product> allProduct = productRepository.findAll();
        List<Product> expectList = List.of(
                new Product(1, "test", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000),
                new Product(2, "BBQ 3만원치킨", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU", 30000),
                new Product(3, "1번가 떡볶이", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcc4HmNF6vetLFAWsBWluAHmCdeDs74ZmsFw&usqp=CAU", 12200),
                new Product(4, "PC방 라면", "https://health.chosun.com/site/data/img_dir/2020/09/07/2020090702900_0.jpg", 7700));

        Assertions.assertThat(allProduct).containsExactly(expectList.toArray(Product[]::new));
    }

    @Test
    public void delete() {

        productRepository.deleteById(1);

        List<Product> allProduct = productRepository.findAll();
        List<Product> expectList = List.of(
                new Product(2, "BBQ 3만원치킨", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU", 30000),
                new Product(3, "1번가 떡볶이", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcc4HmNF6vetLFAWsBWluAHmCdeDs74ZmsFw&usqp=CAU", 12200),
                new Product(4, "PC방 라면", "https://health.chosun.com/site/data/img_dir/2020/09/07/2020090702900_0.jpg", 7700));

        Assertions.assertThat(allProduct).containsExactly(expectList.toArray(Product[]::new));
    }
    @Test
    public void save() {

        Product test = new Product(7, "test", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000);
        productRepository.save(test);

        List<Product> allProduct = productRepository.findAll();

        for (Product product : allProduct) {
            System.out.println("product.getName() = " + product);

        }
        List<Product> expectList = List.of(
                new Product(1, "단짠단짠 피자", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000),
                new Product(2, "BBQ 3만원치킨", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU", 30000),
                new Product(3, "1번가 떡볶이", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcc4HmNF6vetLFAWsBWluAHmCdeDs74ZmsFw&usqp=CAU", 12200),
                new Product(4, "PC방 라면", "https://health.chosun.com/site/data/img_dir/2020/09/07/2020090702900_0.jpg", 7700),
                new Product(5, "test", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000)
        );

        Assertions.assertThat(allProduct).containsExactly(expectList.toArray(Product[]::new));
    }
}