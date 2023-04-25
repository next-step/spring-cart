package cart.cartitem.web.response;

import cart.cartitem.application.dto.CartItemInformation;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CartPageCartItemResponse {
    private final String name;
    private final String image;
    private final Long price;

    public CartPageCartItemResponse(String name, String image, Long price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static List<CartPageCartItemResponse> ofCartItemInformations(List<CartItemInformation> cartItemInformations) {
        return cartItemInformations.stream()
                .map(CartPageCartItemResponse::fromCartItemInformation)
                .collect(toList());
    }

    private static CartPageCartItemResponse fromCartItemInformation(CartItemInformation cartItemInformation) {
        return new CartPageCartItemResponse(
                cartItemInformation.getProductName(),
                cartItemInformation.getProductImage(),
                cartItemInformation.getProductPrice()
        );
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
