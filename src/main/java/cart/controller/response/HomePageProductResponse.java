package cart.controller.response;

import cart.dto.HomePageProduct;

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

    public static List<HomePageProductResponse> ofHomePageProducts(List<HomePageProduct> homePageProducts) {
        return homePageProducts
                .stream()
                .map(HomePageProductResponse::fromHomePageProduct)
                .collect(Collectors.toList());
    }

    private static HomePageProductResponse fromHomePageProduct(HomePageProduct homePageProduct) {
        return new HomePageProductResponse(
                homePageProduct.getId(),
                homePageProduct.getName(),
                homePageProduct.getImage(),
                homePageProduct.getPrice()
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
