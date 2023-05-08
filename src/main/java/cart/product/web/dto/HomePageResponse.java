package cart.product.web.dto;

import cart.product.application.dto.ProductInformation;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageResponse {

    private final Long id;
    private final String name;
    private final String image;
    private final int price;

    public HomePageResponse(Long id, String name, String image, int price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static HomePageResponse from(ProductInformation productInformation) {
        return new HomePageResponse(
            productInformation.getId(),
            productInformation.getName(),
            productInformation.getImage(),
            productInformation.getPrice());
    }

    public static List<HomePageResponse> from(List<ProductInformation> productInformations) {
        return productInformations.stream()
            .map(HomePageResponse::from)
            .collect(Collectors.toUnmodifiableList());
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

    public int getPrice() {
        return price;
    }
}
