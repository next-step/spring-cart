package cart.product.web.response;

import cart.product.application.dto.ProductInformation;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageProductResponse {
    private final Long id;
    private final String name;
    private final String image;
    private final Long price;

    public HomePageProductResponse(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static List<HomePageProductResponse> ofProductInformations(List<ProductInformation> productInformations) {
        return productInformations
                .stream()
                .map(HomePageProductResponse::fromProductInformation)
                .collect(Collectors.toList());
    }

    private static HomePageProductResponse fromProductInformation(ProductInformation productInformation) {
        return new HomePageProductResponse(
                productInformation.getId(),
                productInformation.getName(),
                productInformation.getImage(),
                productInformation.getPrice()
        );
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

    public Long getPrice() {
        return price;
    }
}
