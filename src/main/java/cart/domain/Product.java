package cart.domain;

import cart.exception.ErrorType;
import cart.exception.ServiceException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String image;
    private Money price;

    private Product(Long id, String name, String image, Money price) {
        validate(name, image, price);
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static Product of(String name, String image, long price) {
        return Product.builder()
                .name(name)
                .image(image)
                .price(Money.of(price))
                .build();
    }

    private void validate(String name, String image, Money price) {
        validateName(name);
        validateImage(image);
        validatePrice(price);
    }

    private void validateName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new ServiceException(ErrorType.INVALID_PRODUCT_NAME);
        }
    }

    private void validateImage(String image) {
        if (!StringUtils.hasText(image)) {
            throw new ServiceException(ErrorType.INVALID_PRODUCT_IMAGE);
        }
    }

    private void validatePrice(Money price) {
        if (Objects.isNull(price)) {
            throw new ServiceException(ErrorType.INVALID_PRODUCT_PRICE);
        }
    }

    public void updateId(long id) {
        this.id = id;
    }

    public void update(String name, String image, long price) {
        this.name = name;
        this.image = image;
        this.price = Money.of(price);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getPrice() {
        return price.getLongValue();
    }

    public BigDecimal price() {
        return price.getValue();
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String name;
        private String image;
        private Money price;

        public ProductBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ProductBuilder price(Money price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(id, name, image, price);
        }
    }

}
