package cart.product.web.response;

import cart.product.application.dto.ProductInformation;

import java.util.List;
import java.util.stream.Collectors;

public class AdminPageProductResponse {
    private final Long id;
    private final String name;
    private final String image;
    private final Long price;

    public AdminPageProductResponse(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static List<AdminPageProductResponse> ofProductInformations(List<ProductInformation> productInformations) {
        return productInformations
                .stream()
                .map(AdminPageProductResponse::fromProductInformation)
                .collect(Collectors.toList());
    }

    private static AdminPageProductResponse fromProductInformation(ProductInformation productInformation) {
        return new AdminPageProductResponse(
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
