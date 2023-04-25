package cart.product.web.response;

import cart.product.application.dto.ProductInformation;

public class AdminProductInformationResponse {
    private Long id;
    private String name;
    private String image;
    private Long price;

    public AdminProductInformationResponse(Long id, String name, String image, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static AdminProductInformationResponse fromProductInformation(ProductInformation productInformation) {
        return new AdminProductInformationResponse(
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
