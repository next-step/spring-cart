package cart.api.controller;

import cart.api.dto.ProductRequest;
import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        productRepository.deleteAll();
        Product product1 = new Product("치킨", 1000, "https://static.com?size=1024x1024");
        Product product2 = new Product("샐러드", 10000, "https://static1.com?size=1024x1024");
        Product product3 = new Product("피자", 2000, "https://static2.com?size=1024x1024");
        productRepository.insert(product1);
        productRepository.insert(product2);
        productRepository.insert(product3);
    }

    @Test
    void getAll() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void create() {
        ProductRequest request = new ProductRequest("food", 1000, "https://static.wtable.co.kr/image/production/service/recipe/2167/829d72bf-2523-4355-837b-33fd80cadf17.jpg?size=1024x1024");

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("유효하지 않은 생성 요청에는 예외를 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(InvalidProductsArgumentsProvider.class)
    void createWithException(ProductRequest request) {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/admin")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void update() {
        Product product = new ArrayList<>(productRepository.findAll()).get(0);
        ProductRequest request = new ProductRequest("food", 1000, "https://static.wtable.co.kr/image/production/service/recipe/2167/829d72bf-2523-4355-837b-33fd80cadf17.jpg?size=1024x1024");

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put("/admin/{id}", product.getId())
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("유효하지 않은 수정 요청에는 예외를 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(InvalidProductsArgumentsProvider.class)
    void updateWithException(ProductRequest request) {
        Product product = new ArrayList<>(productRepository.findAll()).get(0);

        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .put("/admin/{id}", product.getId())
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete() {
        Product product = new ArrayList<>(productRepository.findAll()).get(0);
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/admin/{id}", product.getId())
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("존재하지 않는 상품을 삭제하려하면 예외를 반환한다.")
    @Test
    void deleteWithException() {
        var result = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/admin/{id}", 100)
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    static class InvalidProductsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(new ProductRequest("", 1000, "http://a.com")),
                    Arguments.of(new ProductRequest("이름", -1000, "http://b.com")),
                    Arguments.of(new ProductRequest("이름", 2000, null))
            );
        }
    }
}
